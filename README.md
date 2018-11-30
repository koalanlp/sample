## KoalaNLP 사용 샘플

이 Repository는 사용 샘플만을 담고 있습니다.
KoalaNLP의 코드, 사용법, API는 아래에서 보실 수 있습니다.

* [사용법](https://koalanlp.github.io/koalanlp/usage/)
* [Kotlin/Java/Scala API Doc](https://koalanlp.github.io/koalanlp/api/koalanlp/index.html)
* [Scala Implicit Support](https://koalanlp.github.io/scala-support/)
* [Python API Doc](https://koalanlp.github.io/python-support/)
* [NodeJS API Doc](https://koalanlp.github.io/nodejs-support/)

### Kotlin 1.3+

* 문장 분리 기능 사용 예시 [SentenceSplitUsageKt](https://github.com/koalanlp/sample/blob/master/kotlin/src/main/kotlin/sample/SentenceSplitUsage.kt)
* 품사분석기 사용 예시 [TaggerUsageKt](https://github.com/koalanlp/sample/blob/master/kotlin/src/main/kotlin/sample/TaggerUsage.kt)
* 의존구문분석 예시 [DepParserUsageKt](https://github.com/koalanlp/sample/blob/master/kotlin/src/main/kotlin/sample/DepParserUsage.kt)
* 사전 사용 예시 [DictionaryUsageKt](https://github.com/koalanlp/sample/blob/master/kotlin/src/main/kotlin/sample/DictionaryUsage.kt)
* ETRI 분석기 사용 예시 [ETRIApiUsageKt](https://github.com/koalanlp/sample/blob/master/kotlin/src/main/kotlin/sample/ETRIApiUsage.kt)
* 확장 기능 사용 예시 [ExtensionUsageKt](https://github.com/koalanlp/sample/blob/master/kotlin/src/main/kotlin/sample/ExtensionUsage.kt)

각 기능을 실행해보려면 git clone 이후 clone된 디렉터리에서 다음과 같이 입력해보세요. (예: SentenceSplitUsageKt)

```bash
./gradlew -DmainClass=sample.SentenceSplitUsageKt :kotlin:run
```

### Java 8+

* 문장 분리 기능 사용 예시 [SentenceSplitJava](https://github.com/koalanlp/sample/blob/master/java/src/main/java/sample/SentenceSplitJava.java)
* 품사분석기 사용 예시 [TaggerJava](https://github.com/koalanlp/sample/blob/master/java/src/main/java/sample/TaggerJava.java)
* 의존구문분석 예시 [DepParserJava](https://github.com/koalanlp/sample/blob/master/java/src/main/java/sample/DepParserJava.java)
* 사전 사용 예시 [DictionaryJava](https://github.com/koalanlp/sample/blob/master/java/src/main/java/sample/DictionaryJava.java)
* ETRI 분석기 사용 예시 [ETRIApiJava](https://github.com/koalanlp/sample/blob/master/java/src/main/java/sample/ETRIApiJava.java)
* 확장 기능 사용 예시 [ExtensionJava](https://github.com/koalanlp/sample/blob/master/java/src/main/java/sample/ExtensionJava.java)

각 기능을 실행해보려면 git clone 이후 clone된 디렉터리에서 다음과 같이 입력해보세요. (예: SentenceSplitJava)

```bash
./gradlew -DmainClass=sample.SentenceSplitJava :java:run
```

### Scala 2.11, 2.12

* 문장 분리 기능 사용 예시 [SentenceSplitScala](https://github.com/koalanlp/sample/blob/master/scala/src/main/scala/sample/SentenceSplitScala.scala)
* 품사분석기 사용 예시 [TaggerScala](https://github.com/koalanlp/sample/blob/master/scala/src/main/scala/sample/TaggerScala.scala)
* 의존구문분석 예시 [DepParserScala](https://github.com/koalanlp/sample/blob/master/scala/src/main/scala/sample/DepParserScala.scala)
* 사전 사용 예시 [DictionaryScala](https://github.com/koalanlp/sample/blob/master/scala/src/main/scala/sample/DictionaryScala.scala)
* ETRI 분석기 사용 예시 [ETRIApiScala](https://github.com/koalanlp/sample/blob/master/scala/src/main/scala/sample/ETRIApiScala.scala)
* 확장 기능 사용 예시 [ExtensionScala](https://github.com/koalanlp/sample/blob/master/scala/src/main/scala/sample/ExtensionScala.scala)

각 기능을 실행해보려면 git clone 이후 clone된 디렉터리에서 다음과 같이 입력해보세요. (예: SentenceSplitScala)

```bash
./gradlew -DmainClass=sample.SentenceSplitScala :scala:run
```

### Python 3

* 문장 분리 기능 사용 예시 [sentence_split_usage](https://github.com/koalanlp/sample/blob/master/python/sample/sentence_split_usage.py)
* 품사분석기 사용 예시 [tagger_usage](https://github.com/koalanlp/sample/blob/master/python/sample/tagger_usage.py)
* 의존구문분석 예시 [dep_parser_usage](https://github.com/koalanlp/sample/blob/master/python/sample/dep_parser_usage.py)
* 사전 사용 예시 [dictionary_usage](https://github.com/koalanlp/sample/blob/master/python/sample/dictionary_usage.py)
* ETRI 분석기 사용 예시 [ETRI_api_usage](https://github.com/koalanlp/sample/blob/master/python/sample/ETRI_api_usage.py)
* 확장 기능 사용 예시 [extension_usage](https://github.com/koalanlp/sample/blob/master/python/sample/extension_usage.py)

각 기능을 실행해보려면 (1) pip로 `Cython`과 `koalanlp`를 설치하고, (2) git clone 이후 clone된 디렉터리에서 다음과 같이 입력해보세요. (예: sentence_split_usage)

```bash
python3 python/sample/sentence_split_usage.py
```

### NodeJS 6.4.1+

(현재 준비중입니다.)
