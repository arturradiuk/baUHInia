package ioc

import database.IMapObject
import database.managers.MapObjectManager

class ServiceContainer {
    companion object{
        @JvmStatic
        fun getMapObjectManager() : IMapObject{
            return MapObjectManager()
        }
    }
}