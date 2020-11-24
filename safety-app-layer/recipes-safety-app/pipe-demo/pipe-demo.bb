SUMMARY = "IPC by pipe for AGL cluster demo safety workload"
DESCRIPTION = "Jochen layers it on ^^"

#that seems to be necessary, the license here makes no sense, I just fumbled it so it would compile ;)
LICENSE="GPL2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

#giving the source as a git repository, and branch
#Once we have sorted out the repo location issue, this will move there
SRC_URI = "git://github.com/Jochen-Kall/Safety-app.git;branch=main;protocol=https;"

#set the revision to "latest" to make development more convenient
SRCREV = "${AUTOREV}"

PV = "1.0+git${SRCPV}"

#Location of the source during building
S  = "${WORKDIR}/git"

#build instructions
do_compile(){
	bbwarn "here I am"
	gcc ${S}/Safety-signal-source.c -o Signalsource
}

#installation routine, does nothing but copy the text file to the root directiory of the image for testing
do_install() {
    bbwarn "package name appearently is:" ${PN}
    install -m 0755 -d ${D}/usr/bin/
    install -m 0755 Signalsource ${D}/usr/bin/
    #finde den kack nicht, mal schauen ob es dahinkommt -> Funzt
#    install -m 0755 Signalsource ${D}
}

#Packaging information: tell the system that the copied file belongs to this package, otherwise it belongs to no package, and bitbake cries
\FILES_${PN} += "Signalsource"


#python do_build() {
#    bb.plain("***********************************************");
#    bb.plain("*                                             *");
#    bb.plain("*  Example recipe created by bitbake-layers   *");
#    bb.plain("*                                             *");
#    bb.plain("***********************************************");
#}
