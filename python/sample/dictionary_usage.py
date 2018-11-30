from koalanlp.Util import initialize
from koalanlp.proc import Dictionary
from koalanlp.types import POS
from koalanlp import API

initialize(KMR='LATEST', KKMA='LATEST')  #: HNN, EUNJEON, KKMA, OKT

dict = Dictionary(API.KMR)
dict.addUserDictionary(("하동균", POS.NNP))
dict.addUserDictionary(("나비야", POS.NNP))

# print(dict.contains("하동균", POS.NNP, POS.NNG))
print(("하동균", POS.NNP) in dict)
print(', '.join(str(item) for item in dict.getNotExists(True, ("하동균", POS.NNP))))

print("# 접사 목록")
# for word in dict.getBaseEntries(POS.isAffix):
#     print("%s (Tag=%s)" % (word[0], str(word[1])))

print("# 사용자 사전 목록")
for word in dict.getItems():
    print("%s (Tag=%s)" % (word[0], str(word[1])))

print("---- 타 분석기 사전 불러오기 ----")
dict.importFrom(Dictionary(API.KKMA), False, lambda t: t.isNoun())
print("# 사용자 사전 목록 (30개)")
for word in list(dict.getItems())[:30]:
    print("%s (Tag=%s)" % (word[0], str(word[1])))
