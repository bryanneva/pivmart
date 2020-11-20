import {ProductApi} from "./product/ProductApi";
import {CatalogApi} from "./catalog/CatalogApi";
import {Catalog} from "./catalog/Catalog";
import {CartApi} from "./cart/CartApi";
import {AuthApi} from "./auth/AuthApi";


interface Apis {
  catalogApi: CatalogApi
  productApi: ProductApi
  cartApi: CartApi
  authApi: AuthApi
}

export class Gateway {
  readonly catalogApi: CatalogApi;
  readonly productApi: ProductApi;
  readonly cartApi: CartApi;
  readonly authApi: AuthApi;

  constructor(paramOverrides: Partial<Apis> = {}) {
    const params = Object.assign({
      catalogApi: new CatalogApi(),
      productApi: new ProductApi(),
      cartApi: new CartApi(),
      authApi: new AuthApi(),
    }, paramOverrides);

    this.catalogApi = params.catalogApi;
    this.productApi = params.productApi;
    this.cartApi = params.cartApi;
    this.authApi = params.authApi;
  }

  init(): Promise<Catalog[]> {
    return this.catalogApi.getCategories();
  }
}
