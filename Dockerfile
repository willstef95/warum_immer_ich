FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
RUN apt-get update && apt-get install -y \
    libxrender1 libxtst6 libgl1-mesa-glx libgtk-3-0 \
    libcanberra-gtk-module libcanberra-gtk3-module

ENV DISPLAY=host.docker.internal:0
WORKDIR /warum_immer_ich
ADD . /warum_immer_ich
#EXPOSE 8080
CMD sbt run