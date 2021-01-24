package maps.api;

import org.jetbrains.skija.*;

import java.io.IOException;

public class Noise {
    private final int[][] grad3;
    private final int[] p;
    private final int[] perm;

    public static void main(String[] args) throws IOException {
        double w = 50;
        var surface = Surface.makeRasterN32Premul((int)w, (int)w);
        var canvas = surface.getCanvas();
        canvas.clear(0xFFFFFFFF);
        var noise = new Noise();
        double gridSize = 24;

        gridSize = 20;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < w; j++) {
                var val = (int)Math.floor((noise.turbulence(i/gridSize,j/gridSize,0, w) + 1) * 127);
                if(val < 85){
                    //road
                    canvas.drawPoint(i,j, new Paint().setColor(Color.makeRGB(0, 0, 0)));
                }
                else if(val < 125){
                    //beton
                    canvas.drawPoint(i,j, new Paint().setColor(0xFF1f2226));
                }
                else{
                    // zieleń
                    canvas.drawPoint(i,j, new Paint().setColor(0xFF7cfc00));
                }
            }
        }

        byte[] pngBytes = surface.makeImageSnapshot().encodeToData().getBytes();
        java.nio.file.Files.write(java.nio.file.Path.of("F:\\io\\output.png"), pngBytes);
    }

    public double turbulence(double x, double y, double z, double w){
        var t = -.5;
        for (var f = 1 ; f <= w/12 ; f *= 2){ // W = Image width in pixels
            t += Math.abs(this.noise(x,y,z) / f);
        }
        return t;
    }

    public double noise(double x, double y, double z){
        int X = (int) Math.floor(x);
        int Y = (int) Math.floor(y);
        int Z = (int) Math.floor(z);

        x = x - X;
        y = y - Y;
        z = z - Z;

        X = X & 255;
        Y = Y & 255;
        Z = Z & 255;

        var gi000 = this.perm[X+this.perm[Y+this.perm[Z]]] % 12;
        var gi001 = this.perm[X+this.perm[Y+this.perm[Z+1]]] % 12;
        var gi010 = this.perm[X+this.perm[Y+1+this.perm[Z]]] % 12;
        var gi011 = this.perm[X+this.perm[Y+1+this.perm[Z+1]]] % 12;
        var gi100 = this.perm[X+1+this.perm[Y+this.perm[Z]]] % 12;
        var gi101 = this.perm[X+1+this.perm[Y+this.perm[Z+1]]] % 12;
        var gi110 = this.perm[X+1+this.perm[Y+1+this.perm[Z]]] % 12;
        var gi111 = this.perm[X+1+this.perm[Y+1+this.perm[Z+1]]] % 12;

        var n000= this.dot(this.grad3[gi000], x, y, z);
        var n100= this.dot(this.grad3[gi100], x-1, y, z);
        var n010= this.dot(this.grad3[gi010], x, y-1, z);
        var n110= this.dot(this.grad3[gi110], x-1, y-1, z);
        var n001= this.dot(this.grad3[gi001], x, y, z-1);
        var n101= this.dot(this.grad3[gi101], x-1, y, z-1);
        var n011= this.dot(this.grad3[gi011], x, y-1, z-1);
        var n111= this.dot(this.grad3[gi111], x-1, y-1, z-1);

        var u = this.fade(x);
        var v = this.fade(y);
        var w = this.fade(z);

        var nx00 = this.mix(n000, n100, u);
        var nx01 = this.mix(n001, n101, u);
        var nx10 = this.mix(n010, n110, u);
        var nx11 = this.mix(n011, n111, u);

        var nxy0 = this.mix(nx00, nx10, v);
        var nxy1 = this.mix(nx01, nx11, v);
        // Interpolate the two last results along z
        var nxyz = this.mix(nxy0, nxy1, w);

        return nxyz;
    }

    public double dot(int[] g, double x, double y, double z){
        return g[0]*x + g[1]*y + g[2]*z;
    }

    public double mix(double a, double b, double t) {
        return (1.0-t)*a + t*b;
    }

    public double fade(double t) {
        return t*t*t*(t*(t*6.0-15.0)+10.0);
    }

    public Noise(){
        this.grad3 = new int[][]{new int[]{1,1,0},new int[]{-1,1,0},new int[]{1,-1,0},new int[]{-1,-1,0},
                                 new int[]{1,0,1},new int[]{-1,0,1},new int[]{1,0,-1},new int[]{-1,0,-1},
                                 new int[]{0,1,1},new int[]{0,-1,1},new int[]{0,1,-1},new int[]{0,-1,-1}};

        this.p = new int[256];
        for (var i=0; i<256; i++) {
            this.p[i] = (int)Math.floor(Math.random()*256);
        }

        this.perm = new int[512];
        for(var i=0; i<512; i++) {
            this.perm[i]=this.p[i & 255];
        }
    }
}
