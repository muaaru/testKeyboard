# testKeyboard

Androidで画面を開かれると同時にキーボードを表示したい場合の実装のテストベット

onResumeでキーボードを表示しようとしても表示されたりされなかったり不安定である

下記のようにdelayさせると動作するようになるが、これは対症療法である

``` kotlin
    view.postDelayed({
      showSoftInput()
    }, 100)
```

これはViewのFocusだけでなく、その画面がWindowのFocusを取得している必要があるためで
onResumeのタイミングではまだWindowのFocusが取得できていない

そのためonWindowFocusChangedでFocusが取得できてからキーボードを表示することで確実に表示できるようにする


``` kotlin
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d("DEBUG" , "onWindowFocusChanged hasFocus : $hasFocus")
        if(hasFocus) {
            binding.editText.setSelection(selectionStart,selectionEnd)
            showSoftInput(binding.editText)
        } else {
            selectionStart = binding.editText.selectionStart
            selectionEnd = binding.editText.selectionEnd
            binding.editText.clearFocus()
        }
    }
```
