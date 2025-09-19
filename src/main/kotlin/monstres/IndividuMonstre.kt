package org.example.monstres

import org.example.dresseur.Entraineur
import kotlin.random.Random

val variation = mutableListOf(-2, 2)
val variationpv = mutableListOf(-5, 5)

class IndividuMonstre(
    var id: Int,
    var nom: String,
    var espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    explnit: Double
) {
    var ran = Random.nextInt(variation.first(), variation.last() + 1)
    var ranpv = Random.nextInt(variationpv.first(), variationpv.last() + 1)
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + ran
    var defense: Int = espece.baseDefense + ran
    var viteest: Int = espece.baseVitesse + ran
    var attaquespe: Int = espece.baseAttaqueSpe + ran
    var defensespe: Int = espece.baseDefenseSpe + ran
    var pvMax: Int = espece.basePv + ranpv
    var potentiel: Double = 0.5
    var exp: Double = 0.0
    /**
     *  @property pv  Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field=nouveauPv
        }
    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Int {
        return (100*(niveau-1)) * 2 * 2
    }
}