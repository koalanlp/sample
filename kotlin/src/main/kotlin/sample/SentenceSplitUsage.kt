package sample

import kr.bydelta.koala.okt.SentenceSplitter
//import kr.bydelta.koala.hnn.SentenceSplitter

fun main(args: Array<String>) {
    val splitter = SentenceSplitter()

    while (true){
        print("분석할 문장을 입력하세요>> ")
        val text = readLine()

        if (text == null || text.isBlank())
            break

        val sentences: List<String> = splitter(text)
        sentences.forEachIndexed{ i, sent ->
            println("[$i] $sent")
        }
    }
}