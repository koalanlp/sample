from koalanlp.Util import initialize
from koalanlp import ExtUtil

initialize(CORE="LATEST")

# 한글 여부 확인
print("ExtUtil.isHangul('가') = %s" % (str(ExtUtil.isHangul('가'))))  #: [True]
print("ExtUtil.isHangul('ㄱ') = %s" % (str(ExtUtil.isHangul('ㄱ'))))  #: [True]
print("ExtUtil.isHangul('a') = %s" % (str(ExtUtil.isHangul('a'))))  #: [False]

print("ExtUtil.isHangulEnding(\"갤럭시S는\") = %s" % (ExtUtil.isHangulEnding("갤럭시S는")))  #: True
print("ExtUtil.isHangulEnding(\"abc\") = %s" % (ExtUtil.isHangulEnding("abc")))  #: False
print("ExtUtil.isHangulEnding(\"보기 ㄱ\") = %s" % (ExtUtil.isHangulEnding("보기 ㄱ")))  #: True

print("ExtUtil.isCompleteHangul('가') = %s" % (ExtUtil.isCompleteHangul('가')))  #: [True]
print("ExtUtil.isCompleteHangul('ㄱ') = %s" % (ExtUtil.isCompleteHangul('ㄱ')))  #: [False]
print("ExtUtil.isCompleteHangul('a') = %s" % (ExtUtil.isCompleteHangul('a')))  #: [False]

# 종성으로 끝나는지 확인
print("ExtUtil.isJongsungEnding('각') = %s" % (ExtUtil.isJongsungEnding('각')))  #: True
print("ExtUtil.isJongsungEnding('가') = %s" % (ExtUtil.isJongsungEnding('가')))  #: False
print("ExtUtil.isJongsungEnding('m') = %s" % (ExtUtil.isJongsungEnding('m')))  #: False

# 초, 중, 종성 한글 자모 값으로 분해
print("ExtUtil.getChosung('가') = %s" % (ExtUtil.getChosung('가')))  #: 'ㄱ'에 해당하는 초성 문자 [\u1100]
print("ExtUtil.getJungsung('가') = %s" % (ExtUtil.getJungsung('가')))  #: 'ㅏ'에 해당하는 초성 문자 [\u1161]
print("ExtUtil.getJongsung('가') = %s" % (ExtUtil.getJongsung('가')))  #: [None]
print("ExtUtil.getJongsung('각') = %s" % (ExtUtil.getJongsung('각')))  #: 'ㄱ'에 해당하는 종성문자 [\u11A8]
print("ExtUtil.getChosung('ㄱ') = %s" % (ExtUtil.getChosung('ㄱ')))  #: [None]
print("ExtUtil.dissembleHangul('가') = %s" % (ExtUtil.dissembleHangul('가')))  #: '\u1100\u1161' (ᄀ ᅡ)
print("ExtUtil.dissembleHangul('각') = %s" % (ExtUtil.dissembleHangul('각')))  #: '\u1100\u1161\u11A8' (ᄀ ᅡ ᄀ)
print("ExtUtil.dissembleHangul(\"가각\") = %s" % (
    ExtUtil.dissembleHangul("가각")))  #: "\u1100\u1161\u1100\u1161\u11A8" (문자 사이를 띄워서 표시하면: 'ᄀ ᅡ ᄀ ᅡ ᆨ')

# 종성 한글 자모값으로 변환
print("ExtUtil.ChoToJong['ㄵ'] = %s" % (ExtUtil.ChoToJong['ㄵ']))  #: \u11AC

# 한글 자모 범위에 속하는 초, 중, 종성인지 확인
print("ExtUtil.isChosungJamo('ㄱ') = %s" % (ExtUtil.isChosungJamo('ㄱ')))  #: [False]
print("ExtUtil.isChosungJamo('\u1100') = %s" % (ExtUtil.isChosungJamo('\u1100')))  #: [True]
print("ExtUtil.isJungsungJamo('ㅏ') = %s" % (ExtUtil.isJungsungJamo('ㅏ')))  #: [False]
print("ExtUtil.isJungsungJamo('\u1161') = %s" % (ExtUtil.isJungsungJamo('\u1161')))  #: [True]
print("ExtUtil.isJongsungJamo('ㄱ') = %s" % (ExtUtil.isJongsungJamo('ㄱ')))  #: [False]
print("ExtUtil.isJongsungJamo('\u11A8') = %s" % (ExtUtil.isJongsungJamo('\u11A8')))  #: [True]

# 한글 자모 결합
print("ExtUtil.assembleHangulTriple('\u1100', '\u1161', None) = %s" % (
    ExtUtil.assembleHangulTriple('\u1100', '\u1161', None)))  #: '가'
print("ExtUtil.assembleHangulTriple('\u1100', '\u1161', '\u11AB') = %s" % (
    ExtUtil.assembleHangulTriple('\u1100', '\u1161', '\u11AB')))  #: '간'
print("ExtUtil.assembleHangul(\"\u1100\u1161\u1102\u1161\u11ab\") = %s" % (
    ExtUtil.assembleHangul("\u1100\u1161\u1102\u1161\u11ab")))  #: "가난"
print("ExtUtil.assembleHangulTriple(jung='\u1161') = %s" % (ExtUtil.assembleHangulTriple(jung='\u1161')))  #: '아'
print("ExtUtil.assembleHangulTriple('\u1100', None, '\u11A8') = %s" % (
    ExtUtil.assembleHangulTriple('\u1100', None, '\u11A8')))  #: '극'

print("ExtUtil.alphaToHangul(\"ABCD\") = %s" % (ExtUtil.alphaToHangul("ABCD")))  #: "에이비씨디"
print("ExtUtil.alphaToHangul(\"갤럭시S\") = %s" % (ExtUtil.alphaToHangul("갤럭시S")))  #: "갤럭시에스"
print("ExtUtil.alphaToHangul(\"cup\") = %s" % (ExtUtil.alphaToHangul("cup")))  #: "씨유피"

print("ExtUtil.isAlphaPronounced(\"에스비에스\") = %s" % (ExtUtil.isAlphaPronounced("에스비에스")))  #: True
print("ExtUtil.isAlphaPronounced(\"갤럭시에스\") = %s" % (ExtUtil.isAlphaPronounced("갤럭시에스")))  #: False

print("ExtUtil.hangulToAlpha(\"갤럭시에스\") = %s" % (ExtUtil.hangulToAlpha("갤럭시에스")))  #: "갤럭시S"
print("ExtUtil.hangulToAlpha(\"에이디에이치디\") = %s" % (ExtUtil.hangulToAlpha("에이디에이치디")))  #: "ADHD"

print("ExtUtil.isHanja('國') = %s" % (ExtUtil.isHanja('國')))  #: True
print("ExtUtil.isCJKHanja('國') = %s" % (ExtUtil.isCJKHanja('國')))  #: True

print("ExtUtil.hanjaToHangul(\"國篇\") = %s" % (ExtUtil.hanjaToHangul("國篇")))  #: "국편"
print("ExtUtil.hanjaToHangul(\"國篇은 오늘\") = %s" % (ExtUtil.hanjaToHangul("國篇은 오늘")))  #: "국편은 오늘"
print("ExtUtil.hanjaToHangul(\"300 兩의 돈\") = %s" % (ExtUtil.hanjaToHangul("300 兩의 돈")))  #: "300 냥의 돈"
print("ExtUtil.hanjaToHangul(\"樂園\") = %s" % (ExtUtil.hanjaToHangul("樂園")))  #: "낙원" (두음법칙)
