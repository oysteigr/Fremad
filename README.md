Nettsiden bruker Maven og Spring.
Den kjører på tomcat7.

Installer eclipse gjennom ubuntu software center.

Installer git:

  $ sudo apt-get install git
  $ cd ~/workspace
  $ git clone git@github.com:oysteigr/Fremad.git
  
Så importer prosjektet i eclipse

Kjør dette for å sette opp tomcat:
  $ wget http://archive.apache.org/dist/tomcat/tomcat-7/v7.0.54/bin/apache-tomcat-7.0.54.tar.gz
  $ tar -xvf apache-tomcat-7.0.54.tar.gz
  $ sudo mv apache-tomcat-7.0.54 /usr/local/

Sett opp tomcat-users.xml:
  $ sudo gedit /usr/local/apache-tomcat-7.0.54/conf/tomcat-users.xml
  
Merk alt og lim inn:
<?xml version='1.0' encoding='utf-8'?>
<tomcat-users>
	<role rolename="manager-gui"/>
	<role rolename="manager-script"/>
	<role rolename="admin"/>
	<role rolename="manager"/>
	<user username="managerGui" password="gui" roles="manager-gui"/>
	<user username="manager" password="script" roles="manager-script"/>
	<user username="admin" password="admin" roles="admin,manager,manager-gui,manager-script"/>
</tomcat-users>

og settings.xml i ~/.m2/settings.xml:

  $ gedit ~/.m2/settings.xml
  
Merk alt og lim inn:
<settings>
	<servers>
	<server>
		<id>mytomcat</id>
		<username>admin</username>
		<password>admin</password>
	</server>
</servers>
</settings>

  $ /usr/local/apache-tomcat-7.0.54/bin/startup.sh

Sjekk at det funker ved å logge inn på http://localhost:8080/manager/text
User: amdin pw: admin

Hvis du får logget inn, gå inn i mappa der fremad ligger:
  $ cd ~/workspace/fremad
  $ mvn package
  $ mvn tomcat:deploy (første gang)
  $ mvn tomcat:redeploy (hvis du har kjørt deoply før)

Gå inn på http://localhost:8080/no.fremad
Ser du noe? Da har du klart å sette opp alt!
