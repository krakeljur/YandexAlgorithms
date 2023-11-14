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