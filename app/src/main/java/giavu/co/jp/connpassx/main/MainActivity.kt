package giavu.co.jp.connpassx.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import giavu.co.jp.connpassx.R
import giavu.co.jp.domain.model.ConnpassSeries
import giavu.co.jp.domain.usecase.FetchConnpassEventUseCase
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val KEY_DATA = "key_data"

        fun createIntent(context: Context, data: ConnpassSeries): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(KEY_DATA, data)
            }
        }
    }

    private val data: ConnpassSeries? by lazy {
        intent.getSerializableExtra(KEY_DATA) as? ConnpassSeries
    }

    private val viewModel: MainViewModel by inject()
    private val fetchConnpassEventUseCase: FetchConnpassEventUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.init(fetchConnpassEventUseCase)
    }
}
