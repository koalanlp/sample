from koalanlp.Util import initialize
from koalanlp.proc import RoleLabeler
from koalanlp import API
import os
import sys

API_KEY = os.getenv('API_KEY') if len(sys.argv) <= 1 else sys.argv[1]

if API_KEY is None:
    print("ETRI 분석기는 API 키 설정이 필요합니다. API_KEY 환경변수를 설정하거나 프로그램 인자로 key를 전달해주세요.")
    exit(1)

initialize(ETRI='LATEST')
labeler = RoleLabeler(API.ETRI, apiKey=API_KEY)
# recognizer = EntityRecognizer(apiKey=API_KEY)
# parser = Parser(apiKey=API_KEY)
# tagger = Tagger(apiKey=API_KEY)

while True:
    text = input("분석할 문장을 입력하세요>> ").strip()

    if len(text) == 0:
        break

    sentences = labeler(text)

    for sent in sentences:
        print("===== Sentence =====")
        print(sent.singleLineString())

        entities = sent.getEntities()
        if len(entities) > 0:
            print("# Named Entities")

            for entity in entities:
                print("[%s]는 %s 유형의 개체명으로, 형태소 [%s]를 포함합니다." % (entity.getSurface(),
                                                                str(entity.getFineLabel()),
                                                                " ".join(str(m) for m in entity)))

        dependencies = sent.getDependencies()
        if len(dependencies) > 0:
            print("# Dependency Parse")

            for edge in dependencies:
                print("[%s]는 [%s]의 %s-%s" % (edge.getDependent().getSurface(),
                                             edge.getGovernor().getSurface() if edge.getGovernor() is not None else "ROOT",
                                             str(edge.getType()),
                                             str(edge.getDepType())))

        roles = sent.getRoles()
        if len(roles) > 0:
            print("# Role Labeling")

            for edge in roles:
                print("[%s]는 [%s]의 %s" % (edge.getArgument().getSurface(),
                                          edge.getPredicate().getSurface(),
                                          str(edge.getLabel())))
