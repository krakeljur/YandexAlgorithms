
fun rearrangement() {

    val n = readln().trim().toInt()

    val isUse = MutableList(n + 1) { false }
    isUse[0] = true

    val res = mutableListOf<String>()
    val prevs = mutableListOf<Int>()

    reqPosition(prevs, res, n, isUse)

    print(res.joinToString("\n"))

}

fun reqPosition(prevs: MutableList<Int>, res: MutableList<String>, n: Int, isUse: MutableList<Boolean>) {

    for (i in 1..<isUse.size) {

        if (!isUse[i]) {
            isUse[i] = true
            prevs.add(i)
            if (prevs.size == isUse.size - 1) {
                res.add(prevs.joinToString(""))
                if (res.size >= 100_000) {
                    println(res.joinToString("\n"))
                    res.clear()
                }
            }
            reqPosition(prevs, res, n - 1, isUse)
            prevs.removeLast()
            isUse[i] = false
        }

    }

}


fun dino() {
    // n <= 10; N - dino count
    val n = readln().trim().toInt()

    val result = reqPositionInZoo(1, n)

    print(result)
}

fun reqPositionInZoo(
    startI: Int,
    n: Int,
    inZoo: Int = 0,
    badColumn: MutableSet<Int> = mutableSetOf(),
    badRow: MutableSet<Int> = mutableSetOf(),
    badCrossPlus: MutableSet<Int> = mutableSetOf(),
    badCrossMinus: MutableSet<Int> = mutableSetOf()
): Int {

    var result = 0

    var nowInZoo = inZoo

    for (i in startI..n) {

        if (i !in badRow) {

            for (j in 1..n) {


                if (j !in badColumn && (i + j) !in badCrossPlus && (j - i) !in badCrossMinus) {
                    nowInZoo++
                    if (nowInZoo == n) {
                        result++
                        nowInZoo--
                    } else {
                        badRow.add(i)
                        badColumn.add(j)
                        badCrossMinus.add(j - i)
                        badCrossPlus.add(j + i)
                        result += reqPositionInZoo(i, n, nowInZoo, badColumn, badRow, badCrossPlus, badCrossMinus)
                        nowInZoo--
                        badRow.remove(i)
                        badColumn.remove(j)
                        badCrossMinus.remove(j - i)
                        badCrossPlus.remove(j + i)
                    }
                }
            }
        }
    }

    return result
}

fun maxCut() {

    val n = readln().trim().toInt()
    val inSecondTeam = MutableList(n) { 1 }
    val matrix = mutableListOf<MutableList<Int>>()

    for (i in 1..n) {
        matrix.add(readln().trim().split(" ").map { it.toInt() }.toMutableList())
    }

    val maxWeight = reqMaxCut(inSecondTeam, n, matrix)

    println(maxWeight.first)
    println(maxWeight.second.joinToString(" "))
}

fun reqMaxCut(
    inSecondTeam: MutableList<Int>,
    n: Int, matrix: MutableList<MutableList<Int>>,
    cur: Int = 0,
    curI: Int = 1
): Pair<Int, List<Int>> {
    var maxWeight = 0
    var ansList = inSecondTeam.toList()
    var weightCurrent = cur
    for (i in curI..<n) {
        if (inSecondTeam[i] == 1) {
            inSecondTeam[i] = 2
            for (j in 0..<n) {
                if (inSecondTeam[j] == 1 && j != i) {
                    weightCurrent += matrix[i][j]
                } else if (inSecondTeam[j] == 2 && j != i) {
                    weightCurrent -= matrix[i][j]
                }
            }
            val nextReq = reqMaxCut(inSecondTeam, n, matrix, weightCurrent, i)
            if (nextReq.first > maxWeight) {
                maxWeight = nextReq.first
                ansList = nextReq.second
            }
            inSecondTeam[i] = 1
            weightCurrent = cur
        }
    }
    return if (maxWeight > weightCurrent)
        Pair(maxWeight, ansList)
    else
        Pair(weightCurrent, inSecondTeam.toMutableList())
}


fun courier() {
    val n = readln().trim().toInt()
    val matrix = mutableListOf<List<Int>>()

    for (i in 1..n) {
        matrix.add(readln().trim().split(" ").map { it.toInt() })
    }

    if (n != 1) {

        val allVariants = mutableListOf<String>()
        val prevs = mutableListOf<Int>()
        val isVisited = MutableList(n + 1) { false }
        isVisited[0] = true
        isVisited[1] = true

        val min = reqCourier(prevs, allVariants, n - 1, isVisited, matrix)
        println("${if (min == 0) -1 else min}")
    } else println(0)
}

fun reqCourier(
    prevs: MutableList<Int>,
    res: MutableList<String>,
    n: Int,
    isUse: MutableList<Boolean>,
    matrix: MutableList<List<Int>>
) : Int {
    var min = 0
    for (i in 2..<isUse.size) {

        if (!isUse[i]) {
            isUse[i] = true
            prevs.add(i-1)
            if (prevs.size == isUse.size - 2) {
                val newWay = prevs.joinToString("")
                var thisWay = matrix[0][newWay[0].digitToInt()]
                if (thisWay != 0) {
                    for (i in 1..<newWay.length) {
                        if (matrix[newWay[i - 1].digitToInt()][newWay[i].digitToInt()] != 0)
                            thisWay += matrix[newWay[i - 1].digitToInt()][newWay[i].digitToInt()]
                        else {
                            return 0
                        }
                    }
                        thisWay += matrix[newWay.last().digitToInt()][0]
                    if ((min < thisWay && min == 0) || (min > thisWay))
                        min = thisWay
                }
            }
            val nextReq = reqCourier(prevs, res, n - 1, isUse, matrix)
            if (nextReq != 0 && ((nextReq < min && min != 0) || (nextReq > min && min == 0)))
                min = nextReq
            prevs.removeLast()
            isUse[i] = false
        }
    }
    return min
}


