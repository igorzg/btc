FROM python:3.7.2-slim
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
CMD ["python", "exchange.py"]