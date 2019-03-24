FROM python:3.7.2-slim
ADD . /code
WORKDIR /code
ENV FLASK_ENV development
ENV FLASK_APP exchange
EXPOSE 5000
RUN pip install -r requirements.txt
CMD ["flask", "run", "-h", "0.0.0.0"]