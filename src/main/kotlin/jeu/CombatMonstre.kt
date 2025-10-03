package org.example.jeu

import org.example.joueur
import org.example.monstres.IndividuMonstre
import org.example.item.Utilisable

class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
    val monstreSauvage: IndividuMonstre
) {
    var round = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean {
        if (monstreJoueur.pv <= 0) {
            return true
        }
        else
            return false
    }

    fun joueurGagne(): Boolean{
        if(monstreSauvage.pv <= 0){
            println("${joueur.nom} a gagné !")
            val gainExp = monstreSauvage.exp * 0.20
            monstreJoueur.exp += gainExp
            println("${monstreJoueur.nom} gagne ${gainExp} exp.")
            return true
        }
        else {
            if (monstreSauvage.entraineur == joueur) {
                println("${monstreSauvage.nom} a été capturé !")
                return true
            }
            else{
                return false
            }
        }
    }

    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {
            monstreSauvage.attaquer()
        }
    }

    fun actionJoueur(): Boolean {
        if (gameOver() == true)
            return false

        else {
            println("Menu d'action : ")
            println("   1. Attaquer")
            println("   2. Utiliser un objet")
            println("   3. Changer de monstre")
            val choix = readln().toInt()
            if (choix == 1) {
                monstreJoueur.attaquer()
            }

            else if (choix == 2) {
                println(joueur.sacAItems)
                println("Choisissez l'objet avec des numéros.")
                val indexChoix = readln().toInt()
                val objetChoisi = joueur.sacAItems[indexChoix]
                if (objetChoisi is Utilisable) {
                    val captureReussie = objetChoisi.utiliser(monstreSauvage)
                    if (captureReussie) {
                        return false
                    }
                    else {return true}
                }
                else {println("Objet inutilisable.")}
            }

            else if (choix == 3) {
                println(joueur.equipeMonstre)
                println("Choisissez le monstre avec des numéros.")
                val indexChoix = readln().toInt()
                val choixMonstre = joueur.equipeMonstre[indexChoix]
                if (choixMonstre.pv > 0) {
                    println("${choixMonstre.nom} remplace ${monstreJoueur.nom}.")
                    monstreJoueur = choixMonstre
                }
            }
            else {
                println("Choix invalide.")
                actionJoueur()
            }
            return true
        }
    }

    fun afficherCombat() {
        println("======== Début round : $round ========")
        println("Niveau: ${monstreSauvage.niveau}")
        println("PV: ${monstreSauvage.pv}/${monstreSauvage.pvMax}")
        println(monstreSauvage.afficheArt())
        println(monstreJoueur.afficheArt(false))
        println("Niveau: ${monstreJoueur.niveau}")
        println("PV: ${monstreJoueur.pv}/${monstreJoueur.pvMax}")
        println("======================================")
    }

    fun jouer() {
        val joueurPlusRapide = (monstreJoueur.vitesse >= monstreSauvage.vitesse)
        val continuer = actionJoueur()
        afficherCombat()
        if (joueurPlusRapide) {
            if (continuer == false) {return}
            else {actionAdversaire()}
        }
        else {
            actionAdversaire()
            if (gameOver() == false) {
                if (continuer == false) {return}
            }
            else {return}
        }
        return
    }

    /**
     * Lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les PV
     * de tous ses monstres.
     */
    fun lanceCombat() {
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }

}