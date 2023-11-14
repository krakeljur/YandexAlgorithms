fun partition() {

    val n = readln().toInt()
    val seqStr = readln()

    if (seqStr.isEmpty()) {
        println(0)
        println(0)
    } else {
        val seq = seqStr.split(" ").map { it.toInt() }.toMutableList()
        val x = readln().toInt()

        if (seq.size > 1) {

            var left = 0
            var right = seq.size - 1

            while (left < right) {
                if (seq[left] >= x)
                    if (seq[right] < x) {
                        val forSwap = seq[right]
                        seq[right] = seq[left]
                        seq[left] = forSwap
                    } else
                        right--
                else
                    left++
            }

            if (seq[right] >= x) {
                println(right)
                println(n - right)
            } else {
                println(right + 1)
                println(n - right - 1)
            }
        } else {
            println(if (seq[0] < x) 1 else 0)
            println(if (seq[0] >= x) 1 else 0)
        }
    }
}

fun partitionReq(l: Int, r: Int, seq: MutableList<Int>) {

    if (r - l + 1 > 1 && seq.subList(l, r + 1).toSet().size > 1) {

        var forSwap = (Math.random() * (r - l + 1)).toInt() + l
        val x = seq[forSwap]
        var n = l
        var eq = l
        var gr = l

        seq[forSwap] = seq[eq]
        seq[eq] = x

        while (n < r + 1) {
            if (seq[n] < x) {
                forSwap = seq[n]
                seq[n] = seq[gr]
                seq[gr] = seq[eq]
                seq[eq] = forSwap
                gr++
                eq++
            } else if (seq[n] == x) {
                forSwap = seq[n]
                seq[n] = seq[gr]
                seq[gr] = forSwap
                gr++
            }
            n++
        }
        if (eq - l + 1 > 1)
            partitionReq(l, eq, seq)
        if (r - gr + 1 > 1)
            partitionReq(gr, r, seq)
    }
}


fun quickSort() {
    val n = readln().toInt()
    if (n != 0) {
        val seq = readln().split(" ").map { it.toInt() }.toMutableList()

        partitionReq(0, seq.size - 1, seq)
        println(seq.joinToString(" "))
    }
}

fun merge() {
    val n = readln().toInt()
    val firstStr = readln()
    val m = readln().toInt()
    val secStr = readln()
    if (firstStr.isNotEmpty()) {
        if (secStr.isNotEmpty()) {
            val ansList = mutableListOf<Int>()
            val firstList = firstStr.split(" ").map { it.toInt() }
            val secList = secStr.split(" ").map { it.toInt() }
            var first = 0
            var sec = 0

            while (first < firstList.size && sec < secList.size) {
                if (firstList[first] <= secList[sec]) {
                    ansList.add(firstList[first])
                    first++
                } else {
                    ansList.add(secList[sec])
                    sec++
                }
            }
            while (first < firstList.size) {
                ansList.add(firstList[first])
                first++
            }
            while (sec < secList.size) {
                ansList.add(secList[sec])
                sec++
            }
            println(ansList.joinToString(" "))
        } else {
            println(firstStr)
        }
    } else if (secStr.isNotEmpty()) {
        println(secStr)
    }
}

fun mergeReq(
    list: List<Int>
): MutableList<Int> {

    val ansList = mutableListOf<Int>()

    if (list.size == 1)
        return ansList.also { it.add(list[0]) }

    val leftList = mergeReq(list.subList(0, (list.size-1) / 2 + 1))
    val rightList = mergeReq(list.subList((list.size-1) / 2 + 1, list.size))

    var leftPoint = 0
    var rightPoint = 0

    while (leftPoint < leftList.size && rightPoint < rightList.size) {
        if (leftList[leftPoint] <= rightList[rightPoint]) {
            ansList.add(leftList[leftPoint])
            leftPoint++
        } else {
            ansList.add(rightList[rightPoint])
            rightPoint++
        }
    }
    while (leftPoint < leftList.size) {
        ansList.add(leftList[leftPoint])
        leftPoint++
    }
    while (rightPoint < rightList.size) {
        ansList.add(rightList[rightPoint])
        rightPoint++
    }


    return ansList
}

fun mergeSort() {
    val n = readln().toInt()
    if (n != 0) {
        val list = readln().split(" ").map { it.toInt() }

        println(mergeReq(list).joinToString(" "))
    }
}


// Код работает, но нечитаем, можно реализовать приятнее
fun radixSort() {

    val n = readln().toInt()
    val originalList = mutableListOf<String>()
    val bucket0 = mutableListOf<String>()
    val bucket1 = mutableListOf<String>()
    val bucket2 = mutableListOf<String>()
    val bucket3 = mutableListOf<String>()
    val bucket4 = mutableListOf<String>()
    val bucket5 = mutableListOf<String>()
    val bucket6 = mutableListOf<String>()
    val bucket7 = mutableListOf<String>()
    val bucket8 = mutableListOf<String>()
    val bucket9 = mutableListOf<String>()
    var phasesCount = 0

    for (i in 0..<n) {
        val numString = readln()
        if (phasesCount < numString.length)
            phasesCount = numString.length
        originalList.add(numString)
    }
    println("Initial array:")
    println(originalList.joinToString(", "))
    println("**********")

    for (i in phasesCount-1 downTo 0) {
        for (j in 0..<originalList.size) {
            when (originalList[j][i]) {
                '0' -> bucket0.add(originalList[j])
                '1' -> bucket1.add(originalList[j])
                '2' -> bucket2.add(originalList[j])
                '3' -> bucket3.add(originalList[j])
                '4' -> bucket4.add(originalList[j])
                '5' -> bucket5.add(originalList[j])
                '6' -> bucket6.add(originalList[j])
                '7' -> bucket7.add(originalList[j])
                '8' -> bucket8.add(originalList[j])
                '9' -> bucket9.add(originalList[j])
            }
        }
        println("Phase ${(i - phasesCount) * (-1)}")
        println("Bucket 0: ${if (bucket0.isEmpty()) "empty" else bucket0.joinToString(", ")}")
        println("Bucket 1: ${if (bucket1.isEmpty()) "empty" else bucket1.joinToString(", ")}")
        println("Bucket 2: ${if (bucket2.isEmpty()) "empty" else bucket2.joinToString(", ")}")
        println("Bucket 3: ${if (bucket3.isEmpty()) "empty" else bucket3.joinToString(", ")}")
        println("Bucket 4: ${if (bucket4.isEmpty()) "empty" else bucket4.joinToString(", ")}")
        println("Bucket 5: ${if (bucket5.isEmpty()) "empty" else bucket5.joinToString(", ")}")
        println("Bucket 6: ${if (bucket6.isEmpty()) "empty" else bucket6.joinToString(", ")}")
        println("Bucket 7: ${if (bucket7.isEmpty()) "empty" else bucket7.joinToString(", ")}")
        println("Bucket 8: ${if (bucket8.isEmpty()) "empty" else bucket8.joinToString(", ")}")
        println("Bucket 9: ${if (bucket9.isEmpty()) "empty" else bucket9.joinToString(", ")}")
        println("**********")
        originalList.clear()
        originalList.addAll(bucket0)
        originalList.addAll(bucket1)
        originalList.addAll(bucket2)
        originalList.addAll(bucket3)
        originalList.addAll(bucket4)
        originalList.addAll(bucket5)
        originalList.addAll(bucket6)
        originalList.addAll(bucket7)
        originalList.addAll(bucket8)
        originalList.addAll(bucket9)

        bucket0.clear()
        bucket1.clear()
        bucket2.clear()
        bucket3.clear()
        bucket4.clear()
        bucket5.clear()
        bucket6.clear()
        bucket7.clear()
        bucket8.clear()
        bucket9.clear()
    }
    println("Sorted array:")
    println(originalList.joinToString(", "))
}