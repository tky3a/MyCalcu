package ja.tutorial.mycalcu

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvInput: TextView
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    // ViewファイルでonClick="onDigit"　を設定している場合に発火
    fun onDigit(view: View) {
        // nullではない場合に実行
        // appendで追加
        // view as ButtonでButtonの情報を全て取得してそれぞれのボタンのtextをappendで追加
        tvInput.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    // ViewファイルでonClick="onClear"　を設定している場合に発火
    fun onClear(view: View) {
        tvInput?.text = ""
    }

    // 小数点を追加する関数
    fun onDecimalPoint(view: View) {
        // 最後が数値である && ドットではない場合　フラグを切り替る
        if (lastNumeric && !lastDot) {
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
}