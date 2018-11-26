package sample

import kr.bydelta.koala.data.Sentence
import kr.bydelta.koala.etri.RoleLabeler

//import kr.bydelta.koala.etri.EntityRecognizer
//import kr.bydelta.koala.etri.Parser
//import kr.bydelta.koala.etri.Tagger


fun main(args: Array<String>) {
    val API_KEY = args.getOrNull(0) ?: System.getenv("API_KEY")

    if (API_KEY == null) {
        println("ETRI 분석기는 API 키 설정이 필요합니다. API_KEY 환경변수를 설정하거나 프로그램 인자로 key를 전달해주세요.")
        System.exit(1)
    }

    val labeler = RoleLabeler(API_KEY)
//    val recognizer = EntityRecognizer(API_KEY)
//    val parser = Parser(API_KEY)
//    val tagger = Tagger(API_KEY)

    while (true) {
        print("분석할 문장을 입력하세요>> ")
        val text = readLine()

        if (text == null || text.isBlank())
            break

        val sentences: List<Sentence> = labeler(text)
        sentences.forEachIndexed { i, sent ->
            println("===== Sentence #$i =====")
            println(sent.singleLineString())

            val entities = sent.getEntities()
            if (entities != null) {
                println("# Named Entities")
                for (entity in entities)
                    println("[${entity.surface}]는 ${entity.fineLabel} 유형의 개체명으로, 형태소 [${entity.joinToString()}]를 포함합니다.")
            }

            val dependencies = sent.getDependencies()
            if (dependencies != null) {
                println("# Dependency Parse")
                for (edge in dependencies)
                    println("[${edge.dependent.surface}]는 [${edge.governor?.surface
                            ?: "ROOT"}]의 ${edge.type}-${edge.depType}")
            }

            val roles = sent.getRoles()
            if (roles != null) {
                println("# Role Labeling")
                for (edge in roles)
                    println("[${edge.argument.surface}]는 [${edge.predicate.surface}]의 ${edge.label}")
            }
        }
    }
}