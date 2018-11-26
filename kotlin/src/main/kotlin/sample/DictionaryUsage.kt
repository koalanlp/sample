package sample

import kr.bydelta.koala.POS
import kr.bydelta.koala.kmr.Dictionary
//import kr.bydelta.koala.eunjeon.Dictionary
//import kr.bydelta.koala.hnn.Dictionary
//import kr.bydelta.koala.kkma.Dictionary
//import kr.bydelta.koala.okt.Dictionary

fun main(args: Array<String>) {
    Dictionary += "하동균" to POS.NNP
    Dictionary.addUserDictionary("나비야" to POS.NNP)

    println(Dictionary.contains("하동균"))
    println("하동균" to POS.NNP in Dictionary)
    println(Dictionary.getNotExists(true, "하동균" to POS.NNP).joinToString())

    println("# 접사 목록")
    Dictionary.getBaseEntries { it.isAffix() }.forEach {
        println("${it.first} (Tag=${it.second})")
    }

    println("# 사용자 사전 목록")
    Dictionary.getItems().forEach {
        println("${it.first} (Tag=${it.second})")
    }

    println("---- 타 분석기 사전 불러오기 ----")
    Dictionary.importFrom(kr.bydelta.koala.kkma.Dictionary)
    println("# 사용자 사전 목록 (30개)")
    Dictionary.getItems().asSequence().take(30).forEach {
        println("${it.first} (Tag=${it.second})")
    }
}