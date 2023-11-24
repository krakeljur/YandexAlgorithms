import sun.security.ec.point.ProjectivePoint.Mutable

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

// ДОДЕЛАТЬ
fun maxCut() {

    val n = readln().trim().toInt()
    val inSecondTeam = MutableList(n) {false}
    val matrix = mutableListOf<MutableList<Int>>()
    var maxWeight = 0

    for (i in 1..n) {
        matrix.add(readln().trim().split(" ").map { it.toInt() }.toMutableList())
    }
    


}

//СДЕЛАТЬ
/*fun courier(){
    val n = readln().trim().toInt()
    val matrix = mutableListOf<List<Int>>()

    for (i in 1..n) {
        matrix.add(readln().trim().split(" ").map { it.toInt() })
    }

    val visited = MutableList(n) {false}

    var min = 0

    min = courierReq(min, visited, matrix)

    println("$min")
}

fun courierReq(baseMin: Int, visited : MutableList<Boolean>, roads: MutableList<List<Int>>) : Int {




    return 0
}*/
