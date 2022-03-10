package www.smktelkommlg.mybackground

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.lifecycleScope
import www.smktelkommlg.mybackground.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        val btnStart = bindingMain.btnStart
        val tvStatus = bindingMain.tvStatus
        val tvHidden = bindingMain.hiddenTv

        btnStart.setOnClickListener{
            lifecycleScope.launch(Dispatchers.Default){
                try{
                    btnStart.setBackgroundColor(Color.MAGENTA)
                    for (i in 0..10){
                        delay(500)
                        val percentage = i*10

                        withContext(Dispatchers.Main){
                            if(percentage == 100){
                                tvStatus.text = getString(R.string.task_completed)
                                tvHidden.visibility = View.VISIBLE
                                btnStart.setBackgroundColor(Color.BLUE)
                            }else{
                                tvStatus.text = String.format(getString(R.string.compressing), percentage)
                                tvHidden.visibility = View.GONE
                            }
                        }
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }

    }
}