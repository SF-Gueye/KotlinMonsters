package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.espece_Aquamy
import org.example.espece_Flamkip
import org.example.espece_Springleaf
import org.example.joueur
import org.example.monstres.IndividuMonstre
import org.example.monde.Zone
import org.example.route1

class Partie(
    id: Int,
    joueur: Entraineur,
    zone: Zone
) {
    fun choixStarter() {
        val monstre1 = IndividuMonstre(1, "springleaf", espece_Springleaf, null, 1500.0)
        val monstre2 = IndividuMonstre(2, "flamkip", espece_Flamkip, null, 1500.0)
        val monstre3 = IndividuMonstre(3, "aquamy", espece_Aquamy, null, 1500.0)

        monstre1.afficheDetail()
        monstre2.afficheDetail()
        monstre3.afficheDetail()

        println("Choix de starter: ")
        println("   1. Springleaf")
        println("   2. Flamkip")
        println("   3. Aquamy")

        val starter: IndividuMonstre
        val choix = readln().toInt()
        if (choix == 1) {
            starter = monstre1
        }
        else if (choix == 2) {
            starter = monstre2
        }
        else {
            starter = monstre3
        }

        starter.renommer()
        joueur.equipeMonstre.add(starter)
        starter.entraineur = joueur
    }

    fun modifierOrdreEquipe() {
        if (joueur.equipeMonstre.size < 2) {
            println("Impossible de modifier l'ordre.")
        }
        else {
            println("Modifier l'ordre d'action: ")
            println("Exemple: 1. ${joueur.equipeMonstre[0].nom}")
            println("         2. ${joueur.equipeMonstre[1].nom}")
            println("Cela remplacera le 2eme monstre de l'équipe par le premier.")
            print("Veuillez renseignez le premier monstre: ")
            val choix = readln().toInt()
            println("Veuillez entrez le second monstre: ")
            val choix2 = readln().toInt()

            var monstreChoix = joueur.equipeMonstre[choix]
            val monstreChoixGarder = joueur.equipeMonstre[choix]
            var monstreChoix2 = joueur.equipeMonstre[choix2]

            monstreChoix = monstreChoix2
            monstreChoix2 = monstreChoixGarder
        }
    }

    fun examineEquipe() { //TODO Terminer la fonction examineEquipe.
        val m = modifierOrdreEquipe()
        //val q = jouer()
        println("Equipe: ")
        for (monstre in joueur.equipeMonstre) {
            var position = 1
            println("${position}. ${monstre.nom}")
            position += 1
        }

        println("Choisissez le monstre dont vous voulez les infos: ")
        val indexChoix = readln().toInt()
        val monstreChoisi = joueur.equipeMonstre[indexChoix-1]
        monstreChoisi.afficheDetail()
        monstreChoisi.afficheArt()

        println("Modifier l'ordre:")

    }

    fun jouer() { //TODO Finir jouer
        println("Menu de jeu: ")
        println("    1. Rencontrer un monstre sauvage")
        println("    2. Examiner l'équipe de monstres")
        println("    3. Aller à la zone suivante")
        println("    4. Aller à la zone précédente")

        val choix = readln().toInt()
        if (choix == 1) {
            //Zone().rencontrerMonstre()
        }
        else if (choix == 2) {
            examineEquipe()
        }
        else if (choix == 3) {
            //if (zoneSuivante != null) {zoneActuelle = zoneSuivante}
        }
        else if (choix == 4) {
            //if (zonePrécédente != null) {zoneActuelle = zonePrécédente}
        }
    }
}