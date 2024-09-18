# -*- mode: ruby -*-
# vi: set ft=ruby :

VM_BOX = "generic/ubuntu2204"
VM_PROVIDER = "virtualbox"
VM_CPU = 2
VM_MEMORY = 4096

Vagrant.configure("2") do |config|
  config.vm.define "vm" do |machine|
    machine.vm.box = VM_BOX
    machine.vm.synced_folder "./", "/mnt/vagrant"

    machine.vm.provider VM_PROVIDER do |v|
      v.cpus = VM_CPU
      v.memory = VM_MEMORY
    end

    machine.vm.provision "shell", inline: <<-SHELL
      sudo chown -R vagrant: /opt
      sudo apt update
      sudo apt upgrade -y
      sudo apt install -y unzip

      addToBashrc() {
        grep -qxF "$1" /home/vagrant/.bashrc || echo "$1" >> /home/vagrant/.bashrc
      }

      # Install Java
      sudo apt install -y openjdk-17-jdk
      addToBashrc 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64'
      addToBashrc 'export PATH=$JAVA_HOME/bin:$PATH'

      # Install Gradle
      GRADLE_LATEST_VERSION=$(curl -sL https://services.gradle.org/versions/current | python3 -c "import sys, json; print(json.load(sys.stdin)['version'])")
      curl -L https://services.gradle.org/distributions/gradle-${GRADLE_LATEST_VERSION}-bin.zip -o /tmp/gradle-${GRADLE_LATEST_VERSION}-bin.zip
      unzip -o -d /opt/gradle /tmp/gradle-${GRADLE_LATEST_VERSION}-bin.zip
      addToBashrc "export GRADLE_HOME=/opt/gradle/gradle-${GRADLE_LATEST_VERSION}"
      addToBashrc 'export PATH=$GRADLE_HOME/bin:$PATH'
    SHELL

  end
end
