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

    fun onOperator(view: View) {
        // tvInputのテキストが存在している場合に処理を行う
        tvInput?.text?.let {
            println("it.toString() ${it.toString()}")
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    // イコールボタンタップ関数
    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
// 文字の先頭が-の場合
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                // マイナスパターン
                if (tvValue.contains("-")) {
                    println(" aiueo")
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    // ()で囲んでからtoStringすれば１行でかける
                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    // ()で囲んでからtoStringすれば１行でかける
                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    // ()で囲んでからtoStringすれば１行でかける
                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                } else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }

                    // ()で囲んでからtoStringすれば１行でかける
                    tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                }
                tvInput.text = removeZeroAfterDot(tvInput.text.toString())
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0")) value = result.substring(0, result.length - 2) // 後ろに文字を取り除く
        return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        // startsWithで先頭の特定文字をチェック
        return if (value.startsWith("")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")

        }

    }
}