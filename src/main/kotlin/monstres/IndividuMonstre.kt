package org.example.monstres

import org.example.dresseur.Entraineur

class IndividuMonstre(
    var id: Int,
    var nom: String,
    var espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    explnit: Double
) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + 2
    var defense: Int = espece.baseDefense + 2
    var viteest: Int = espece.baseVitesse + 2
    var attaquespe: Int = espece.baseAttaqueSpe + 2
    var defensespe: Int = espece.baseDefenseSpe + 2
    var pvMax: Int = espece.basePv + 5
    var potentiel: Double = 0.5
    var exp: Double = 0.0
    var pv: Int = pvMax
}