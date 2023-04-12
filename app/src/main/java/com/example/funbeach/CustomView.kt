package com.example.funbeach

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * CustomView est une classe personnalisée étendant la classe View d'Android.
 * Elle affiche et gère plusieurs cercles colorés qui se déplacent sur l'écran.
 * Les cercles peuvent être déplacés par interaction tactile de l'utilisateur.
 */
class CustomView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    // Paint utilisé pour dessiner les cercles.
    private var mPaint = Paint()

    // Objet compagnon contenant une constante DELTA pour déterminer la vitesse de déplacement des cercles.
    companion object {
        val DELTA = 8
    }

    // Liste mutable des cercles à afficher.
    private val mCircles = mutableListOf<MagicCircle>()

    // Nombre de cercles à créer lors de l'initialisation.
    private val NUM_CIRCLES = 3

    // Liste des couleurs prédéfinies pour les cercles.
    private val colors = listOf(
        Color.parseColor("#F5DEB3"),
        Color.parseColor("#8B4513"),
        Color.parseColor("#006400")
    )

    /**
     * Méthode onDraw responsable du rendu des cercles sur le canvas.
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for ((i, circle) in mCircles.withIndex()) {
            circle.move()
            mPaint.color = circle.color
            canvas?.drawCircle(circle.cx, circle.cy, circle.rad, mPaint)
        }
        invalidate()
    }

    /**
     * Méthode d'initialisation de la vue.
     */
    private fun init() {
        mPaint.color = Color.BLUE
    }

    /**
     * Méthode onLayout appelée lors de la mise en page de la vue.
     * Elle crée les cercles initiaux si la liste est vide.
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (mCircles.isEmpty()) {
            for (i in 0 until NUM_CIRCLES) {
                val color = colors.getOrNull(i) ?: Color.BLUE
                mCircles.add(MagicCircle(width, height, color).apply {
                    cx = Random.nextFloat() * maxX
                    cy = Random.nextFloat() * maxY
                    dx = (DELTA * (if (Random.nextBoolean()) -1 else 1) * Random.nextFloat())
                    dy = (DELTA * (if (Random.nextBoolean()) -1 else 1) * Random.nextFloat())
                })
            }
        }
    }

    /**
     * Méthode onTouchEvent gérant les interactions tactiles de l'utilisateur avec la vue.
     * Cette méthode ajoute un nouveau cercle lors d'un appui, et permet de déplacer le cercle le plus proche lors d'un mouvement tactile.
     *
     * @param event Objet MotionEvent représentant l'événement tactile.
     * @return Boolean Retourne toujours true, indiquant que l'événement a été géré.
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            // Lors d'un appui, ajoute un nouveau cercle avec une couleur et une vitesse aléatoires.
            MotionEvent.ACTION_DOWN -> {
                val color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                val randomSpeed = (Random.nextFloat() * (DELTA - 1)) + 1
                mCircles.add(MagicCircle(width, height, color).apply {
                    cx = Random.nextFloat() * maxX
                    cy = Random.nextFloat() * maxY
                    dx = (DELTA * (if (Random.nextBoolean()) -1 else 1) * Random.nextFloat())
                    dy = (DELTA * (if (Random.nextBoolean()) -1 else 1) * Random.nextFloat())
                })
            }
            // Lors d'un mouvement tactile, déplace le cercle le plus proche de la position du doigt.
            MotionEvent.ACTION_MOVE -> {
                var closestCircle: MagicCircle? = null
                var closestDist = Float.MAX_VALUE

                // Parcourt tous les cercles pour trouver le cercle le plus proche de la position du doigt.
                for (circle in mCircles) {
                    val dx = event.x - circle.cx
                    val dy = event.y - circle.cy
                    val dist = sqrt(dx * dx + dy * dy)
                    // Mise à jour de la condition pour sélectionner le cercle le plus proche.
                    if (dist < closestDist) {
                        closestCircle = circle
                        closestDist = dist
                    }
                }

                // Déplace le cercle le plus proche à la position du doigt.
                closestCircle?.let {
                    it.cx = event.x
                    it.cy = event.y
                }
            }
        }
        return true
    }
}


