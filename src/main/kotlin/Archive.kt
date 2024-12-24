data class Archive(
    val name: String,
    val listOfNotes: MutableList<Note> = mutableListOf()
)
