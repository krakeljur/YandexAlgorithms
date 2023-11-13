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
//TODO("ВАЛИТСЯ НА 13-ом тесте, ИСПРАВИТЬ!!!!")
fun partitionReq(l: Int, r: Int, seq: MutableList<Int>) {

    if (r - l > 1 && seq.subList(l, r + 1).toSet().size > 1) {

        var forSwap = (Math.random() * (r - l + 1)).toInt() + l
        val x = seq[forSwap]
        var n = l
        var eq = l
        var gr = -1

        seq[forSwap] = seq[eq]
        seq[eq] = x

        while (n < seq.size) {
            if (seq[n] < x) {
                forSwap = seq[n]
                if (gr == -1) {
                    seq[n] = seq[eq]
                    seq[eq] = forSwap
                } else {
                    seq[n] = seq[gr]
                    seq[gr] = seq[eq]
                    seq[eq] = forSwap
                    gr++
                }
                eq++
            } else if (seq[n] == x) {
                forSwap = seq[n]
                if (gr != -1) {
                    seq[n] = seq[gr]
                    seq[gr] = forSwap
                    gr++
                }
            } else if (seq[n] > x && gr == -1) {
                gr = n
            }
            n++
        }
        partitionReq(l, eq, seq)
        if (gr != -1)
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