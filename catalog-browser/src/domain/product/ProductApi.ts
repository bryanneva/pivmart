import {Product} from "./Product";

export class ProductApi {
  async getProducts(catalog: string): Promise<Product[]> {
    return fetch(`/api/products?catalog=${catalog}`)
      .then(response => {
        if (!response.ok) {
          throw Error(response.statusText);
        } else {
          return response.json();
        }
      })
  }
}
