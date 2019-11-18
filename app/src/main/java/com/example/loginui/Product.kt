package com.example.loginui

class Product {
    var pId : Int = 0
    var PName : String=""
    var pDesc:String=""
    var pQty:Int=0

    constructor(pId: Int, pName : String, pDesc:String, pQty: Int)
    {
        this.pId=pId
        this.pDesc=pDesc
        this.pQty=pQty
        this.PName=pName
    }


    constructor()
    {

    }

}