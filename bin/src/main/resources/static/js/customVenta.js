tamanoAncho = () => {
    
    let width = document.documentElement.clientWidth
    let venta = document.getElementById('venta')
    let factura = document.getElementById('factura')

    if (width < 768) {
        venta.classList.remove('col-8')
        venta.classList.add('col-12')

        factura.classList.remove('col-4')
        factura.classList.add('col-12')
    }

}