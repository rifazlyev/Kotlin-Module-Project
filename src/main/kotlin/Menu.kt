import Storage.archivesStorage
import java.util.Scanner

data class Menu(
    val header: Map<String, MenuActionCallBack>,
    val footer: Map<String, MenuActionCallBack>,
    var body: Map<String, MenuActionCallBack> = emptyMap()
) {
    fun createArchive() {
        val scanner = Scanner(System.`in`)
        println("Введите название архива")
        while (true) {
            val name = scanner.nextLine()
            if (name.isEmpty()) {
                println("Название архива не может быть пустым")
                continue
            }
            archivesStorage.add(Archive(name))
            body = returnListOfArchives().body
            println("Архив $name создан")
            break
        }
    }

    fun createNotes(archive: Archive) {
        val scanner = Scanner(System.`in`)
        println("Введите название заметки")
        var name: String
        var content: String
        while (true) {
            name = scanner.nextLine()
            if (name.isNotEmpty()) break
            println("Название заметки не может быть пустым")
        }
        println("Введите текст заметки")
        while (true) {
            content = scanner.nextLine()
            if (content.isNotEmpty()) break
            println("Заметка не может быть пустой")
        }
        archive.listOfNotes.add(Note(name, content))
        body = returnListOfNotes(archive).body
        println("Заметка $name создана")
    }

    fun render() {
        val scanner = Scanner(System.`in`)
        println("Введите номер пункта")
        while (true) {
            val actions: Map<String, MenuActionCallBack> = header + body + footer
            val navigation: List<String> = actions.map { it.key }
            navigation.forEachIndexed { index, s -> println("$index. $s") }
            if (!scanner.hasNextInt()) {
                println("Некорректный ввод - введите цифру")
                scanner.nextLine()
                continue
            }
            val result = scanner.nextInt()
            if (result !in 0..navigation.lastIndex) {
                println("Такого пункта меню нет")
                continue
            }
            if (actions[navigation[result]]?.invoke(this) == true) {
                break
            }
        }
    }
}
