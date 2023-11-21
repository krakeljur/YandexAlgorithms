import java.util.*


fun dijkstra() {

    var (n, start, finish) = readln().trim().split(" ").map { it.toInt() }
    val graphMatrix = mutableListOf<List<Int>>()
    val visited = MutableList(n) { false }
    val distance = MutableList(n) { 10000000 }

    finish--
    distance[--start] = 0
    if (start != finish) {
        val heap = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

        for (i in 1..n) {
            if (i - 1 == start)
                heap.add(Pair(0, i - 1))
            else
                heap.add(Pair(10000000, i - 1))
            graphMatrix.add(readln().trim().split(" ").map { it.toInt() })
        }

        var indexOfMin = 0

        for (j in 1..n) {

            do {
                indexOfMin = heap.poll().second
                if (indexOfMin == null)
                    break
            } while (visited[indexOfMin])

            graphMatrix[indexOfMin].forEachIndexed { index, i ->
                if (index != indexOfMin && i != -1 && distance[index] > i + distance[indexOfMin]) {
                    distance[index] = i + distance[indexOfMin]
                    heap.add(Pair(distance[index], index))
                }
            }

            visited[indexOfMin] = true

        }
        println(if (distance[finish] == 10000000) "-1" else distance[finish].toString())
    } else println("0")
}

fun dijkstraWithWay() {

    var (n, start, finish) = readln().trim().split(" ").map { it.toInt() }
    val graphMatrix = mutableListOf<MutableList<Int>>()
    val visited = MutableList(n) { false }
    val distance = MutableList(n) { Pair(10000000, 0) }


    finish--
    distance[--start] = Pair(0, start)
    if (start != finish) {
        val heap = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

        for (i in 1..n) {
            if (i - 1 == start)
                heap.add(Pair(0, i - 1))
            else
                heap.add(Pair(10000000, i - 1))
            distance[i - 1] = Pair(distance[i - 1].first, i - 1)
            graphMatrix.add(readln().trim().split(" ").map { it.toInt() }.toMutableList())
        }

        var indexOfMin = 0

        for (j in 1..n) {

            do {
                indexOfMin = heap.poll().second
                if (indexOfMin == null)
                    break
            } while (visited[indexOfMin])

            graphMatrix[indexOfMin].forEachIndexed { index, i ->
                if (index != indexOfMin && i != -1 && distance[index].first > i + distance[indexOfMin].first) {
                    distance[index] = Pair(i + distance[indexOfMin].first, indexOfMin)
                    heap.add(Pair(distance[index].first, index))
                }
            }

            visited[indexOfMin] = true
        }
        if (distance[finish].first != 10000000) {
            graphMatrix[0].clear()
            graphMatrix[0].add(finish + 1)
            indexOfMin = finish
            while (indexOfMin != start) {
                graphMatrix[0].add(distance[indexOfMin].second + 1)
                indexOfMin = distance[indexOfMin].second
            }
            println(graphMatrix[0].reversed().joinToString(" "))
        } else println("-1")
    } else println("${start + 1}")
}

fun quickDijkstra() {

    val (n, k) = readln().trim().split(" ").map { it.toInt() }
    val roads = MutableList<MutableList<Pair<Long, Int>>>(n + 1) { mutableListOf() }
    val visited = MutableList(n + 1) { false }
    val distance = MutableList(n + 1) { 3_000_000_000_000_000_000L }

    visited[0] = true

    for (i in 1..k) {
        val (start, finish, length) = readln().trim().split(" ").map { it.toInt() }
        if (start != finish) {
            roads[start].add(Pair(length.toLong(), finish))
            roads[finish].add(Pair(length.toLong(), start))
        }
    }
    val (start, finish) = readln().trim().split(" ").map { it.toInt() }

    distance[start] = 0
    if (start != finish) {

        val heap = PriorityQueue<Pair<Long, Int>>(compareBy { it.first })

        for (i in 1..n) {
            if (i == start)
                heap.add(Pair(0L, i))
            else
                heap.add(Pair(3_000_000_000_000_000_000L, i))
        }

        var indexOfMin = 0

        for (j in 1..n) {

            do {
                indexOfMin = heap.poll().second
                if (indexOfMin == null)
                    break
            } while (visited[indexOfMin])

            roads[indexOfMin].forEach { i ->
                if (i.second != indexOfMin && distance[i.second] > i.first + distance[indexOfMin]) {
                    distance[i.second] = i.first + distance[indexOfMin]
                    heap.add(Pair(distance[i.second], i.second))
                }
            }

            visited[indexOfMin] = true

        }
        println(if (distance[finish] == 3_000_000_000_000_000_000L) "-1" else distance[finish].toString())
    } else println("0")
}

fun busesInTheVillage() {

    val n = readln().trim().toInt()
    val roads = MutableList<MutableList<Triple<Long, Int, Long>>>(n + 1) { mutableListOf() }
    val visited = MutableList(n + 1) { false }
    val time = MutableList(n + 1) { 3_000_000_000_000_000_000L }

    visited[0] = true

    val (start, finish) = readln().trim().split(" ").map { it.toInt() }
    if (start != finish) {

        time[start] = 0

        val r = readln().trim().toInt()

        val heap = PriorityQueue<Pair<Long, Int>>(compareBy { it.first }) // first - время, в которое путешественник находится в деревне second

        for (i in 1..r) {
            val (startInd, startTime, finInd, finTime) = readln().trim().split(" ").map { it.toInt() }
            if (startInd != finInd) {
                roads[startInd].add(Triple(startTime.toLong(), finInd, finTime.toLong()))
            }
        }

        for (i in 1..n) {
            if (i == start)
                heap.add(Pair(0L, start))
            else
                heap.add(Pair(3_000_000_000_000_000_000L, i))
        }

        var indexOfMin = 0

        for (j in 1..n) {

            do {
                indexOfMin = heap.poll().second
            } while (visited[indexOfMin])

            roads[indexOfMin].forEach {
                if (it.first >= time[indexOfMin] && time[it.second] > it.third) {
                    time[it.second] = it.third
                    heap.add(Pair(it.third, it.second))
                }
            }
            visited[indexOfMin] = true
        }
        println(if (time[finish] == 3_000_000_000_000_000_000L) "-1" else time[finish].toString())
    } else println("0")
}