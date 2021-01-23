package maps.api

open class ITrackable {
    var state: State = State.CREATED
        protected set
}

enum class State{
    UNCHANGED,
    MODIFIED,
    CREATED,
    DELETED
}