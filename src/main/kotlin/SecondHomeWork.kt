fun substringEqual() {

    val s = readln().toCharArray().map { it.code.toLong() }
    val n = readln().toInt()
    val ansList = mutableListOf<String>()

    val p = 1_000_000_007
    val x = 257

    val hashList = mutableListOf<Long>()
    val xList = mutableListOf<Long>()

    hashList.add(0)
    xList.add(1)


    for (i in s.indices) {
        hashList.add((hashList[i] * x + s[i]) % p)
        xList.add((xList[i] * x) % p)
    }


    for (i in 1..n) {
        var (l, from1, from2) = readln().split(" ").map { it.toInt() }
        from1++
        from2++

        val first =
            (hashList[if (from1 + l - 1 >= s.size) s.size else from1 + l - 1] + hashList[from2 - 1] * xList[l]) % p
        val second =
            (hashList[if (from2 + l - 1 >= s.size) s.size else from2 + l - 1] + hashList[from1 - 1] * xList[l]) % p

        ansList.add(if (first == second) "yes" else "no")

    }
    println(ansList.joinToString("\n"))
}

fun lineBase() {

    val s = readln().toCharArray().map { it.code.toLong() }
    val p = 1_000_000_007
    val x = 257
    val hashList = mutableListOf<Long>()
    val xList = mutableListOf<Long>()

    hashList.add(0)
    xList.add(1)

    for (i in s.indices) {
        hashList.add((hashList[i] * x + s[i]) % p)
        xList.add((xList[i] * x) % p)
    }

    val from1 = 1
    var maxPrefSuf = 0

    for (i in 1..<hashList.size - 1) {
        val from2 = hashList.size - i
        val prefix =
            (hashList[if (from1 + i - 1 >= s.size) s.size else from1 + i - 1] + hashList[from2 - 1] * xList[i]) % p
        val suffix =
            (hashList[if (from2 + i - 1 >= s.size) s.size else from2 + i - 1] + hashList[from1 - 1] * xList[i]) % p
        if (prefix == suffix && i > maxPrefSuf) {
            maxPrefSuf = i
        }
    }

    println(s.size - maxPrefSuf)

}


fun z() {

    val s = readln().toCharArray().map { it.code.toLong() }
    val p = 1_000_000_007
    val x = 257
    val hashList = mutableListOf<Long>()
    val xList = mutableListOf<Long>()

    hashList.add(0)
    xList.add(1)

    for (i in s.indices) {
        hashList.add((hashList[i] * x + s[i]) % p)
        xList.add((xList[i] * x) % p)
    }

    val ansList = mutableListOf<Long>()

    ansList.add(0)

    for (i in 2..<hashList.size) {
        var rightPoint = hashList.size - 1
        var leftPoint = 1

        var length = rightPoint

        if (
            (hashList[if (1 + length - 1 >= s.size) s.size else 1 + length - 1] + hashList[i - 1] * xList[length]) % p ==
            (hashList[if (i + length - 1 >= s.size) s.size else i + length - 1] + hashList[1 - 1] * xList[length]) % p
        )
            ansList.add(length.toLong())
        else if (
            (hashList[if (1 >= s.size) s.size else 1] + hashList[i - 1] * xList[1]) % p !=
            (hashList[if (i >= s.size) s.size else i] + hashList[1 - 1] * xList[1]) % p
        )
            ansList.add(0)
        else {
            while (rightPoint > leftPoint) {
                length = (rightPoint + leftPoint) / 2
                val first =
                    (hashList[if (1 + length - 1 >= s.size) s.size else 1 + length - 1] + hashList[i - 1] * xList[length]) % p
                val second =
                    (hashList[if (i + length - 1 >= s.size) s.size else i + length - 1] + hashList[1 - 1] * xList[length]) % p
                if (first != second) {
                    rightPoint = length
                    length = (rightPoint + leftPoint) / 2
                } else {
                    leftPoint = length
                    length = (rightPoint + leftPoint) / 2
                }
                if (rightPoint - leftPoint == 1) {
                    rightPoint = leftPoint
                    length = leftPoint
                }
            }
            ansList.add(length.toLong())
        }
    }

    println(ansList.joinToString(" "))
}

fun mirror() {

    val (n, colors) = readln().split(" ").map { it.toInt() }

    val cubes = readln().split(" ").map { it.toInt() }
    val hashCubes = mutableListOf<Long>()
    val hashCubesRev = mutableListOf<Long>()
    val p = 1_000_000_007
    val xList = mutableListOf<Long>()
    hashCubes.add(0)
    hashCubesRev.add(0)
    xList.add(1)

    for (i in cubes.indices) {
        hashCubes.add((hashCubes[i] * 10 + cubes[i]) % p)
        xList.add((xList[i] * 10) % p)
        hashCubesRev.add((hashCubesRev[i] * 10 + cubes[n - 1 - i]) % p)
    }

    val ansList = mutableListOf<Int>()

    for (i in 1..n / 2) {
        if (
            (hashCubes[i + i - 1] + hashCubesRev[n - i - 1] * xList[i]) % p ==
            (hashCubesRev[n - 1] + hashCubes[i - 1] * xList[i]) % p
        )
            ansList.add(n - i)
    }
    ansList.reverse()
    ansList.add(n)
    println(ansList.joinToString(" "))

}

fun subPalindromes() {

    val p = 1_000_000_007
    val x = 257
    val hashList = mutableListOf<Long>()
    val hashListRev = mutableListOf<Long>()
    val xList = mutableListOf<Long>()

    hashList.add(0)
    hashListRev.add(0)
    xList.add(1)

    var counter = 0

    val s = readln().toCharArray().also { counter = it.size }.joinToString("#").toCharArray().map { it.code.toLong() }

    for (i in s.indices) {
        hashList.add((hashList[i] * x + s[i]) % p)
        xList.add((xList[i] * x) % p)
        hashListRev.add((hashListRev[i] * 10 + s[s.size - 1 - i]) % p)
    }



    for (i in 2..<hashList.size) {
        //TODO("БИНПОИСК НА НАИБОЛЬШИЙ ПАЛИНДРОМ С ЦЕНТРОМ В ЭТОЙ ТОЧКЕ")

    }

    println(counter)

}