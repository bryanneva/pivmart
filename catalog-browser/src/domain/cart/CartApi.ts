import {CartItem} from "./CartItem";
import {Product} from "../product/Product";

export class CartApi {
  list(): Promise<CartItem[]> {
    return fetch('/api/cart')
      .then(response => response.json());
  }

  add(product: Product): Promise<CartItem> {
    return fetch('/api/cart', {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify(product)
    })
        .then(response => response.json())
        .catch(e => console.error(e) );
  }

  remove(id: string): Promise<void> {
    return fetch(`/api/cart/${id}`, {
      method: 'DELETE',
      mode: 'no-cors'
    })
      .then(response => response.json());
  }

  checkOut() {
    fetch('/api/cart/checkout', {
      method: 'POST',
      mode: 'no-cors'
    });
  }
}
