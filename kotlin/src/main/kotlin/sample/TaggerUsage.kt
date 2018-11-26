package sample

import kr.bydelta.koala.data.Sentence
import kr.bydelta.koala.eunjeon.Tagger
// import kr.bydelta.koala.kmr.Tagger
// import kr.bydelta.koala.etri.Tagger
// import kr.bydelta.koala.eunjeon.Tagger
// import kr.bydelta.koala.arirang.Tagger
// import kr.bydelta.koala.daon.Tagger
// import kr.bydelta.koala.rhino.Tagger
// import kr.bydelta.koala.okt.Tagger
// import kr.bydelta.koala.kkma.Tagger
// import kr.bydelta.koala.hnn.Tagger


fun main(args: Array<String>) {
    val tagger = Tagger()

    while (true){
        print("분석할 문장을 입력하세요>> ")
        val text = readLine()

        if (text == null || text.isBlank())
            break

        val sentences: List<Sentence> = tagger(text)
        sentences.forEachIndexed{ i, sent ->
            println("===== Sentence #$i =====")
            println(sent.surfaceString())

            println("# Analysis Result")
            // println(sent.singleLineString())

            for(word in sent){
                print("Word [${word.id}] ${word.surface} = ")

                for(morph in word){
                    print("${morph.surface}/${morph.tag} ")
                }
                println()
            }
        }
    }
}