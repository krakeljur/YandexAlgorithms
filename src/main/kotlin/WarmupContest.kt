import kotlin.math.*

/*Задана последовательность целых чисел a1, a2, …, an.
Задаются запросы: сказать любой элемент последовательности на отрезке от L до R включительно,
не равный минимуму на этом отрезке.*/
fun notTheMinimum() {

    val m = readln().split(" ")[1].toInt()

    val seq = readln().split(" ").map { it.toInt() }

    val answers = mutableListOf<String>()

    for (i in 1..m) {

        val lAndR = readln().split(" ").map { it.toInt() }

        val newSeqSet = seq.subList(lAndR[0], lAndR[1] + 1).toSortedSet()

        if (newSeqSet.size == 1)
            answers.add("NOT FOUND")
        else
            answers.add(newSeqSet.last().toString())
    }

    for (ans in answers) {
        println(ans)
    }
}


/*Даны две рациональные дроби: a/b и c/d. Сложите их и результат представьте в виде несократимой дроби m/n.
Программа получает на вход 4 натуральных числа a, b, c, d, каждое из которых не больше 100.
Программа должна вывести два натуральных числа m и n такие, что m/n=a/b+c/d и дробь m/n – несократима.*/
fun plusFractions() {

    val fractions = readln().split(" ").map { it.toInt() }

    var b = fractions[1]
    var d = fractions[3]

    while (b != 0 && d != 0) {
        if (b > d)
            b %= d
        else
            d %= b
    }

    var n = fractions[1] * fractions[3] / (b + d)

    var m = fractions[0] * (n / fractions[1]) + fractions[2] * (n / fractions[3])

    b = m
    d = n

    var nod = 0

    while (nod != 1) {

        while (b != 0 && d != 0) {
            if (b > d)
                b %= d
            else
                d %= b
        }

        nod = d + b
        n /= nod
        m /= nod
        b = n
        d = m
    }

    print("$m $n")
}

/*Мэрия Москвы основательно подготовилась к празднованию тысячелетия города в 2147 году, построив под столицей
бесконечную асфальтированную площадку, чтобы заменить все существующие в городе автомобильные дороги.
В память о кольцевых и радиальных дорогах разрешили двигаться по площадке только двумя способами:

В сторону точки начала координат или от неё. При этом из точки начала координат разрешено двигаться
в любом направлении.
Вдоль окружности с центром в начале координат и радиусом, который равен текущему расстоянию до начала
координат. Двигаться вдоль такой окружности разрешается в любом направлении (по или против часовой стрелки).
Вам, как ведущему программисту ответственной инстанции поручено разработать модуль, который будет определять
кратчайший путь из точки A, с координатами (xA, yA) в точку B с координатами (xB, yB). Считайте, что менять
направление движения можно произвольное количество раз, но оно должно всегда соответствовать одному из двух
описанных выше вариантов.*/
//В первой строке ввода заданы четыре целых числа xA, yA, xB и yB, по модулю не превосходящие 106.
//Выведите одно число — минимальное расстояние, которое придётся преодолеть по пути из точки A в точку B,
//если не нарушать правил дорожного движения. Ваш ответ будет принят, если его абсолютная или относительная
// погрешность не превосходит 10-6.
fun moscowTrip() {


    /*
    * Идея - искать три длинны пути и возвращать наименьший:
    * 1. путь прямыми через центер города
    * 2. путь через сначала дугу, а после прямую до точки (для тех случаев, когда конечная точка находится дальше от центра города, чем стартовая)
    * 3. путь через сначала прямую, а после дугу (для тех случаев, в которых конечная точка находится ближе к центру города, чем стартовая)
    * 4. путь через длину дуги (для случаев, когда расстояние от центра у обоих точек равны)
    */

    //xa - 0 ya - 1 xb - 2 yb - 3
    val coordinates = readln().split(" ").map { it.toDouble() }

    val rStart = sqrt((coordinates[0] * coordinates[0] + coordinates[1] * coordinates[1]))
    val rFinish = sqrt((coordinates[2] * coordinates[2] + coordinates[3] * coordinates[3]))

    val lengthTripCenter = rStart + rFinish


    var angle = atan2(coordinates[3], coordinates[2]) - atan2(coordinates[1], coordinates[0])

    while (angle > PI)
        angle -= 2 * PI
    while (angle < -PI)
        angle += 2 * PI

    val lengthTripCircleLine = if (rStart > rFinish) {
        rStart - rFinish + abs(rFinish * angle)
    } else if (rFinish == rStart) {
        abs(rFinish * angle)
    } else {
        rFinish - rStart + abs(rStart * angle)
    }

    println(min(lengthTripCenter, lengthTripCircleLine))
}


/*Задано две строки, нужно проверить, является ли одна анаграммой другой. Анаграммой называется строка, полученная
из другой перестановкой букв.

Формат ввода
Строки состоят из строчных латинских букв, их длина не превосходит 100000. Каждая записана в отдельной строке.

Формат вывода
Выведите "YES" если одна из строк является анаграммой другой и "NO" в противном случае.*/
fun isAnagram() {
    val firstMap = mutableMapOf<Char, Int>()
    val first = readln()
    val secondMap = mutableMapOf<Char, Int>()
    val second = readln()

    if (first.length == second.length) {
        for (i in first.indices) {
            if (firstMap[first[i]] == null)
                firstMap[first[i]] = 0
            firstMap[first[i]] = firstMap[first[i]]!! + 1

            if (secondMap[second[i]] == null)
                secondMap[second[i]] = 0
            secondMap[second[i]] = secondMap[second[i]]!! + 1
        }
        if (firstMap == secondMap)
            println("YES")
        else
            println("NO")
    } else
        println("NO")
}


fun averageLevel() {

    /*
    * Идея - (сумма рейтингов справа от текущего элемента минус нынешний элемент * на кол-во элементов
    * справа) + (текущий элемент * количество элементов слева - сумма элементов слева) = текущее недовольство
    * */

    val n = readln().toInt()

    val studentsRateList = readln().split(" ").map { it.toInt() }.toMutableList()

    var leftSum = 0

    var rightSum = studentsRateList.sum() - studentsRateList[0]



    for (i in studentsRateList.indices) {

        val currentElement = studentsRateList[i]

        studentsRateList[i] = (rightSum - (currentElement * (n - 1 - i))) + (currentElement * i - leftSum)

        if (i < n - 1) {
            rightSum -= studentsRateList[i + 1]
            leftSum += currentElement
        }
    }

    println(studentsRateList.joinToString(" "))


}


fun minLift() {

    /*
    Идея - идти с верхних этажей, хранить нынешнее количество людей в лифте, высчитывать секунды по индексу
    этажа и умножать на количество приездов через % и / (остаток от деления и целочисленное деление) :3
     */

    val maxInLift = readln().toInt()
    val floors = readln().toInt()
    val peoples = mutableListOf<Long>()

    for (i in 1..floors) peoples.add(readln().toLong())

    var inLiftNow = 0L

    var ansSeconds: ULong = 0u



    for (i in peoples.size - 1 downTo 0) {

        if (peoples[i] > 0) {

            if (inLiftNow > 0) {
                ansSeconds++

                if ((peoples[i] + inLiftNow) % maxInLift == 0.toLong()) {
                    ansSeconds += (((peoples[i] + inLiftNow) / maxInLift) * (i + 1) * 2 - i - 1).toULong()
                    inLiftNow = 0
                } else if ((peoples[i] % maxInLift) + inLiftNow < maxInLift) {
                    ansSeconds += ((peoples[i] / maxInLift) * (i + 1) * 2).toULong()
                    inLiftNow += peoples[i] % maxInLift
                } else if ((peoples[i] % maxInLift) + inLiftNow > maxInLift) {
                    ansSeconds += (((peoples[i] + inLiftNow) / maxInLift) * (i + 1) * 2).toULong()
                    inLiftNow = (peoples[i] + inLiftNow) % maxInLift
                }


            } else {
                ansSeconds += ((peoples[i] / maxInLift) * (i + 1) * 2).toULong()
                if (peoples[i] % maxInLift != 0.toLong()) {
                    inLiftNow += peoples[i] % maxInLift
                    ansSeconds += (i + 1).toULong()
                }
            }


        } else if (inLiftNow > 0) {
            ansSeconds++
        }

    }

    if (inLiftNow > 0) ansSeconds++

    println(ansSeconds)

}