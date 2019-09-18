package br.com.dfsaude.util



fun String.isValidNome(): Boolean = this.isNotEmpty()



fun String.isValidEndereco(): Boolean = this.isNotEmpty()


fun String.isValidCEP(): Boolean = this.isNotEmpty() && this.length == 9



fun String.isValidPlaca(): Boolean = this.isNotEmpty() && this.length == 8

fun String.isValidEixos(): Boolean = this.isNotEmpty() && this.length == 1




