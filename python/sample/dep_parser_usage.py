from koalanlp.Util import initialize
from koalanlp.proc import Parser
from koalanlp import API

initialize(KKMA='LATEST')  #: HNN=2.0.4, ETRI=2.0.4

parser = Parser(API.KKMA)

while True:
    text = input("분석할 문장을 입력하세요>> ").strip()

    if len(text) == 0:
        break

    sentences = parser(text)

    for sent in sentences:
        print("===== Sentence =====")
        print(sent.singleLineString())
        print("# Dependency Parse result")

        dependencies = sent.getDependencies()
        if len(dependencies) > 0:
            for edge in dependencies:
                print("[%s]는 [%s]의 %s-%s" % (edge.getDependent().getSurface(),
                                             edge.getGovernor().getSurface() if edge.getGovernor() is not None else "ROOT",
                                             str(edge.getType()),
                                             str(edge.getDepType())))
        else:
            print("(Unexpected) NULL!")
