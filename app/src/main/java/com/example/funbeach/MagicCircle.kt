package com.example.funbeach

import kotlin.random.Random

/**
 * Représente un cercle magique qui se déplace aléatoirement à l'intérieur des limites spécifiées.
 *
 * @param maxX La largeur maximale dans laquelle le cercle peut se déplacer.
 * @param maxY La hauteur maximale dans laquelle le cercle peut se déplacer.
 * @param color La couleur du cercle.
 */
data class MagicCircle(val maxX: Int, val maxY: Int, val color: Int) {

    var cx: Float = 50F
    var cy: Float = 50F
    val rad: Float = 40F
    var dx = (CustomView.DELTA * (if (Random.nextBoolean()) -1 else 1)).toFloat()
    var dy = (CustomView.DELTA * (if (Random.nextBoolean()) -1 else 1)).toFloat()

    /**
     * Fait bouger le cercle d'une étape en fonction de sa vitesse actuelle.
     */
    fun move() {
        when {
            cx !in 0F..maxX.toFloat() -> dx = -dx
            cy !in 0F..maxY.toFloat() -> dy = -dy
        }
        cx += dx
        cy += dy
    }
}