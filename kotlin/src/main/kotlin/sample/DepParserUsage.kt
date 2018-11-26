package sample

import kr.bydelta.koala.data.Sentence
import kr.bydelta.koala.kkma.Parser
// import kr.bydelta.koala.etri.Parser
// import kr.bydelta.koala.hnn.Tagger


fun main(args: Array<String>) {
    val parser = Parser()

    while (true){
        print("분석할 문장을 입력하세요>> ")
        val text = readLine()

        if (text == null || text.isBlank())
            break

        val sentences: List<Sentence> = parser(text)
        sentences.forEachIndexed{ i, sent ->
            println("===== Sentence #$i =====")
            println(sent.singleLineString())

            println("# Dependency Parse result")
            val dependencies = sent.getDependencies()
            if(dependencies != null)
                for(edge in dependencies)
                    println("[${edge.dependent.surface}]는 [${edge.governor?.surface ?: "ROOT"}]의 ${edge.type}-${edge.depType}")
            else
                println("(Unexpected) NULL!")
        }
    }
}