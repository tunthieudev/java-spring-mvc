document.addEventListener('DOMContentLoaded', () => {
    fetch('/seller')  // Gọi API để lấy danh sách sản phẩm từ controller
        .then(response => response.json())  // Chuyển đổi phản hồi từ dạng JSON sang đối tượng JavaScript
        .then(data => {
            const products = data.products;  // Lấy danh sách sản phẩm từ dữ liệu nhận được
            displayProducts(products);  // Hiển thị danh sách sản phẩm trên trang web
        })
        .catch(error => {
            console.error('Error fetching products:', error);
        });
});

function displayProducts(products) {
    const productContainer = document.getElementById('product-container');

    products.forEach(product => {
        const productElement = document.createElement('div');
        productElement.className = 'product';
        productElement.innerHTML = `
            <img src="${product.image}" alt="${product.title}">
            <p>${product.title}</p>
            <p>$${product.price}</p>
            <button onclick="addToCart(${product.id})">Add to Cart</button>
        `;
        productContainer.appendChild(productElement);
    });
}