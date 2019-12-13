from koalanlp.Util import initialize, finalize
from koalanlp.proc import SentenceSplitter
from koalanlp import API

initialize(OKT='LATEST')  #: HNN=2.0.3

splitter = SentenceSplitter(API.OKT)

while True:
    text = input("분석할 문장을 입력하세요>> ").strip()

    if len(text) == 0:
        break

    sentences = splitter(text)

    print("===== Sentence =====")
    for i, sent in enumerate(sentences):
        print("[%s] %s" % (i, sent))

finalize()