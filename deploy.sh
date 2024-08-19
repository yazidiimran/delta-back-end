mvn -DskipTests package
sudo docker build -t yazidi112/delta .
sudo docker push yazidi112/delta

# Connecting to server,pulling image  and running container
USERNAME="ubuntu"
HOST="135.125.183.126"
SCRIPT="echo Connected to server."
SCRIPT="./delta-back-service.sh"
VPS_PASSWORD="yousra@@1987"
sshpass -p ${VPS_PASSWORD} ssh -l ${USERNAME} ${HOST} "${SCRIPT}"



