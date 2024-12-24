import Storage.archivesStorage
import kotlin.system.exitProcess

fun returnListOfArchives(): Menu = Menu(
    header = mapOf(
        "Создать архив" to MenuActionCallBack { menu ->
            menu.createArchive()
            false
        }
    ),
    footer = mapOf(
        "Выход" to MenuActionCallBack {
            exitProcess(0)
        }
    ),
    body = archivesStorage
        .associate { archive ->
            archive.name to MenuActionCallBack {
                returnListOfNotes(archive).render()
                false
            }
        }
)

fun returnListOfNotes(archive: Archive): Menu = Menu(
    header = mapOf(
        "Создать заметку" to MenuActionCallBack { menu ->
            menu.createNotes(archive)
            false
        }
    ),
    footer = mapOf(
        "Назад" to MenuActionCallBack { true }
    ),
    body = archive.listOfNotes.associate { note ->
        note.name to MenuActionCallBack {
            returnActionsWithNotes(note).render()
            false
        }
    }
)

fun returnActionsWithNotes(note: Note): Menu = Menu(
    header = mapOf(
        "Посмотреть заметку" to MenuActionCallBack {
            println(note.content)
            false
        }
    ),
    footer = mapOf(
        "Назад" to MenuActionCallBack { true }
    )
)
