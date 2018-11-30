from koalanlp.Util import initialize
from koalanlp.proc import Tagger
from koalanlp import API

initialize(EUNJEON='LATEST')

tagger = Tagger(API.EUNJEON)

while True:
    text = input("분석할 문장을 입력하세요>> ").strip()

    if len(text) == 0:
        break

    sentences = tagger(text)

    print("===== Sentence =====")
    for i, sent in enumerate(sentences):
        print("===== Sentence #$i =====")
        print(sent.surfaceString())

        print("# Analysis Result")
        # print(sent.singleLineString())

        for word in sent:
            print("Word [%s] %s = " % (word.getId(), word.getSurface()), end='')

            for morph in word:
                print("%s/%s " % (morph.getSurface(), morph.getTag()), end='')

            print()
