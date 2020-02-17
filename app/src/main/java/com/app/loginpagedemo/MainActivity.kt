package com.app.loginpagedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "APP_MainAct"
    }
    private lateinit var mainVM: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //shapeUpdate()

        //initVM()
    }

/*    private fun shapeUpdate() {
        val bg = button.background as? MaterialShapeDrawable
        bg?.let {
            it.shapeAppearanceModel.apply {
                this. = CutCornerTreatment(cornerSize)
            }
            it.shapeAppearanceModel
            it.shapeAppearanceModel.setBottomRightCorner(CornerFamily.ROUNDED, 4)
        }
    }*/

    private fun initVM() {
        mainVM = ViewModelProvider(this).get(MainVM::class.java)
        mainVM.getPostsFromServer()
    }

    override fun onStart() {
        super.onStart()
        callVM()
    }

    private fun callVM() {
        mainVM.postsLiveData.observe(this, Observer {
            Log.d(TAG, "list: $it")
        })
    }
}
