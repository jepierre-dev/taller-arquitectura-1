const API = "/api";

const state = {
  lang: localStorage.getItem("cr-lang") || "es",
  grains: [],
  methods: [],
  orders: [],
};

const $ = (selector, scope = document) => scope.querySelector(selector);
const $$ = (selector, scope = document) => [...scope.querySelectorAll(selector)];

/* ---------- i18n ---------- */

const TEXTS = {
  es: {
    "meta.title": "Coffee Roasters · Tostado de origen colombiano",
    "locale": "es-CO",
    skip: "Saltar al pedido",
    "nav.grains": "Granos",
    "nav.methods": "Métodos",
    "nav.process": "Proceso",
    "nav.orders": "Pedidos",
    "nav.cta": "Pedir ahora",
    "hero.eyebrow": "Tostión artesanal · Lotes pequeños",
    "hero.title": "El grano correcto,<br /><em>tostado a tu método.</em>",
    "hero.lead":
      "Trabajamos microlotes de fincas colombianas y los tostamos según cómo vas a prepararlos. Elige tu origen, elige tu método y nosotros calculamos el perfil exacto.",
    "hero.ctaPrimary": "Armar mi pedido",
    "hero.ctaSecondary": "Ver los orígenes",
    "stats.grains": "Orígenes activos",
    "stats.methods": "Métodos soportados",
    "stats.stock": "Inventario tostado",
    "grains.eyebrow": "Nuestros orígenes",
    "grains.title": "Granos disponibles hoy",
    "grains.sub":
      "Inventario en tiempo real. Lo que ves aquí es exactamente lo que hay tostado en bodega.",
    "grains.empty": "Todavía no hay granos tostados en bodega.",
    "methods.eyebrow": "Cómo lo preparas",
    "methods.title": "Métodos de preparación",
    "methods.sub":
      "Cada método extrae algo distinto del mismo grano. El tiempo es una referencia de extracción.",
    "methods.empty": "Aún no hay métodos de preparación configurados.",
    "process.eyebrow": "Del árbol a la taza",
    "process.title": "Cómo trabajamos",
    "process.step1.title": "Selección de finca",
    "process.step1.text":
      "Compramos directo al caficultor y catamos cada lote antes de comprometerlo.",
    "process.step2.title": "Tostión por perfil",
    "process.step2.text":
      "Ajustamos la curva de tostión según el método con el que lo vas a preparar.",
    "process.step3.title": "Despacho en 24h",
    "process.step3.text":
      "Empacamos con válvula desgasificadora el mismo día que sale del tostador.",
    "order.eyebrow": "Tu pedido",
    "order.title": "Arma tu taza",
    "order.sub":
      "Descontamos el inventario en el momento en que confirmamos el pedido. Si no hay stock suficiente te lo decimos de una, sin cobrarte.",
    "order.perk1": "Molienda calibrada al método elegido",
    "order.perk2": "Tostión de máximo 7 días",
    "order.perk3": "Envío gratis desde 500 g",
    "form.grain": "Grano",
    "form.method": "Método de preparación",
    "form.quantity": "Cantidad",
    "form.grams": "gramos",
    "form.submit": "Confirmar pedido",
    "form.note": "Las respuestas del servidor llegan en el idioma que elijas arriba.",
    "orders.eyebrow": "Trazabilidad",
    "orders.title": "Pedidos realizados",
    "orders.sub": "Todo lo que ha pasado por el tostador, del más reciente al más antiguo.",
    "orders.refresh": "Actualizar",
    "orders.empty": "Todavía no hay pedidos. Arma el primero aquí arriba.",
    "footer.tagline": "Tostadores de origen · Medellín, Colombia",
    "footer.note": "Proyecto académico · Arquitectura hexagonal sobre Quarkus",
    "badge.available": "Disponible",
    "badge.low": "Últimas bolsas",
    "badge.out": "Agotado",
    "card.noNotes": "Lote sin nota de cata registrada.",
    "card.noDescription": "Sin descripción.",
    "meter.inventory": "Inventario",
    "method.extraction": "de extracción",
    "select.grain": "Elige un origen",
    "select.method": "Elige un método",
    "select.grainOption": "{name} — {amount} disponibles",
    "hint.remaining": "Quedan {amount} de {name}.",
    "hint.only": "Solo hay {amount} de {name}.",
    "order.deletedGrain": "Grano retirado del catálogo",
    "order.deletedMethod": "Método retirado",
    "toast.catalogFail": "No pudimos cargar el catálogo",
    "toast.ordersFail": "No pudimos cargar los pedidos",
    "toast.missingTitle": "Falta información",
    "toast.missingBody": "Elige un grano y un método antes de confirmar.",
    "toast.orderBody": "{amount} en camino.",
    "toast.langTitle": "Idioma: español",
    "toast.langBody": "La página y las respuestas del servidor están en español.",
    "unit.kg": "kg",
    "unit.g": "g",
    "unit.min": "min",
    "unit.h": "h",
  },
  en: {
    "meta.title": "Coffee Roasters · Colombian single origin roastery",
    "locale": "en-US",
    skip: "Skip to order",
    "nav.grains": "Beans",
    "nav.methods": "Methods",
    "nav.process": "Process",
    "nav.orders": "Orders",
    "nav.cta": "Order now",
    "hero.eyebrow": "Craft roasting · Small batches",
    "hero.title": "The right bean,<br /><em>roasted for your method.</em>",
    "hero.lead":
      "We source microlots from Colombian farms and roast them for the way you brew. Pick your origin, pick your method and we dial in the exact profile.",
    "hero.ctaPrimary": "Build my order",
    "hero.ctaSecondary": "Browse the origins",
    "stats.grains": "Active origins",
    "stats.methods": "Supported methods",
    "stats.stock": "Roasted inventory",
    "grains.eyebrow": "Our origins",
    "grains.title": "Beans available today",
    "grains.sub": "Live inventory. What you see here is exactly what sits roasted in the warehouse.",
    "grains.empty": "No roasted beans in the warehouse yet.",
    "methods.eyebrow": "How you brew it",
    "methods.title": "Brewing methods",
    "methods.sub":
      "Every method pulls something different out of the same bean. Times are extraction references.",
    "methods.empty": "No brewing methods configured yet.",
    "process.eyebrow": "From tree to cup",
    "process.title": "How we work",
    "process.step1.title": "Farm selection",
    "process.step1.text": "We buy straight from the grower and cup every lot before committing.",
    "process.step2.title": "Profile roasting",
    "process.step2.text": "We adjust the roast curve to the method you will brew it with.",
    "process.step3.title": "Shipped in 24h",
    "process.step3.text": "Packed with a degassing valve the same day it leaves the roaster.",
    "order.eyebrow": "Your order",
    "order.title": "Build your cup",
    "order.sub":
      "We discount inventory the moment the order is confirmed. If there is not enough stock we tell you right away, at no charge.",
    "order.perk1": "Grind calibrated to the chosen method",
    "order.perk2": "Roasted no more than 7 days ago",
    "order.perk3": "Free shipping from 500 g",
    "form.grain": "Bean",
    "form.method": "Brewing method",
    "form.quantity": "Amount",
    "form.grams": "grams",
    "form.submit": "Confirm order",
    "form.note": "Server responses arrive in the language you pick above.",
    "orders.eyebrow": "Traceability",
    "orders.title": "Placed orders",
    "orders.sub": "Everything that went through the roaster, newest first.",
    "orders.refresh": "Refresh",
    "orders.empty": "No orders yet. Build the first one above.",
    "footer.tagline": "Single origin roasters · Medellín, Colombia",
    "footer.note": "Academic project · Hexagonal architecture on Quarkus",
    "badge.available": "In stock",
    "badge.low": "Last bags",
    "badge.out": "Sold out",
    "card.noNotes": "No tasting notes recorded for this lot.",
    "card.noDescription": "No description.",
    "meter.inventory": "Inventory",
    "method.extraction": "extraction",
    "select.grain": "Pick an origin",
    "select.method": "Pick a method",
    "select.grainOption": "{name} — {amount} available",
    "hint.remaining": "{amount} of {name} left.",
    "hint.only": "Only {amount} of {name} available.",
    "order.deletedGrain": "Bean removed from catalog",
    "order.deletedMethod": "Method removed",
    "toast.catalogFail": "We could not load the catalog",
    "toast.ordersFail": "We could not load the orders",
    "toast.missingTitle": "Missing information",
    "toast.missingBody": "Pick a bean and a method before confirming.",
    "toast.orderBody": "{amount} on the way.",
    "toast.langTitle": "Language: English",
    "toast.langBody": "The page and the server responses are now in English.",
    "unit.kg": "kg",
    "unit.g": "g",
    "unit.min": "min",
    "unit.h": "h",
  },
};

function t(key, vars = {}) {
  const template = TEXTS[state.lang][key] ?? key;
  return template.replace(/\{(\w+)\}/g, (_, name) => vars[name] ?? `{${name}}`);
}

function applyTranslations() {
  document.documentElement.lang = state.lang;
  document.title = t("meta.title");

  $$("[data-i18n]").forEach((node) => {
    node.textContent = t(node.dataset.i18n);
  });
  // Solo interpola plantillas propias del diccionario, nunca datos del servidor.
  $$("[data-i18n-html]").forEach((node) => {
    node.innerHTML = t(node.dataset.i18nHtml);
  });
}

/* ---------- api ---------- */

class ApiError extends Error {
  constructor(payload, status) {
    super(payload?.message || `HTTP ${status}`);
    this.details = payload?.error?.details || {};
    this.code = payload?.error?.code;
  }
}

async function api(path, options = {}) {
  const response = await fetch(API + path, {
    ...options,
    headers: {
      "Content-Type": "application/json",
      "Accept-Language": state.lang,
      ...options.headers,
    },
  });

  const payload = await response.json().catch(() => null);
  if (!response.ok) {
    throw new ApiError(payload, response.status);
  }
  return payload;
}

/* ---------- format ---------- */

const grams = (value) =>
  value >= 1000
    ? `${(value / 1000).toLocaleString(t("locale"), { maximumFractionDigits: 1 })} ${t("unit.kg")}`
    : `${value} ${t("unit.g")}`;

const minutes = (value) =>
  value >= 60 ? `${Math.round(value / 60)} ${t("unit.h")}` : `${value} ${t("unit.min")}`;

const dateTime = (iso) =>
  new Intl.DateTimeFormat(t("locale"), { dateStyle: "medium", timeStyle: "short" }).format(
    new Date(iso),
  );

function el(tag, className, text) {
  const node = document.createElement(tag);
  if (className) node.className = className;
  if (text !== undefined) node.textContent = text;
  return node;
}

function stockLevel(available, max) {
  if (available === 0) return { label: t("badge.out"), variant: "badge-out" };
  if (available < max * 0.25) return { label: t("badge.low"), variant: "badge-low" };
  return { label: t("badge.available"), variant: "badge-ok" };
}

/* ---------- toasts ---------- */

function toast(title, detail, variant = "") {
  const node = el("div", `toast ${variant}`.trim());
  node.append(el("strong", null, title));
  if (detail) node.append(el("span", null, detail));

  $("[data-toasts]").append(node);
  setTimeout(() => {
    node.classList.add("is-leaving");
    node.addEventListener("animationend", () => node.remove(), { once: true });
  }, 5200);
}

const detailsText = (details) =>
  Object.entries(details || {})
    .map(([key, value]) => `${key}: ${value}`)
    .join(" · ");

/* ---------- rendering ---------- */

function renderGrains() {
  const container = $("[data-grains]");
  container.replaceChildren();

  if (!state.grains.length) {
    container.append(el("p", "empty", t("grains.empty")));
    return;
  }

  const max = Math.max(...state.grains.map((grain) => grain.totalOnInventory), 1);

  for (const grain of state.grains) {
    const level = stockLevel(grain.totalOnInventory, max);
    const card = el("article", "card reveal");

    const top = el("div", "card-top");
    top.append(el("h3", null, grain.name), el("span", `badge ${level.variant}`, level.label));

    const meter = el("div", "meter");
    const track = el("div", "meter-track");
    const fill = el("div", "meter-fill");
    track.append(fill);

    const label = el("div", "meter-label");
    label.append(el("span", null, t("meter.inventory")), el("span", null, grams(grain.totalOnInventory)));
    meter.append(track, label);

    card.append(top, el("p", null, grain.description || t("card.noNotes")), meter);
    container.append(card);

    requestAnimationFrame(() => {
      fill.style.width = `${Math.max((grain.totalOnInventory / max) * 100, 3)}%`;
    });
  }

  observeReveals(container);
}

function renderMethods() {
  const container = $("[data-methods]");
  container.replaceChildren();

  if (!state.methods.length) {
    container.append(el("p", "empty", t("methods.empty")));
    return;
  }

  for (const method of state.methods) {
    const card = el("article", "card card-method reveal");
    const time = el("p", "method-time", minutes(method.timeInMinutes));
    time.append(el("small", null, t("method.extraction")));

    card.append(
      time,
      el("h3", null, method.name),
      el("p", null, method.description || t("card.noDescription")),
    );
    container.append(card);
  }

  observeReveals(container);
}

function renderOrders() {
  const container = $("[data-orders]");
  container.replaceChildren();

  if (!state.orders.length) {
    container.append(el("p", "empty", t("orders.empty")));
    return;
  }

  const grainById = new Map(state.grains.map((grain) => [grain.id, grain.name]));
  const methodById = new Map(state.methods.map((method) => [method.id, method.name]));

  for (const order of state.orders) {
    const row = el("article", "order-row reveal");

    const main = el("div", "order-main");
    main.append(
      el("strong", null, grainById.get(order.grainId) ?? t("order.deletedGrain")),
      el("span", null, methodById.get(order.preparationMethodId) ?? t("order.deletedMethod")),
    );

    row.append(
      el("span", "order-id", `#${order.id}`),
      main,
      el("span", "order-when", dateTime(order.placedAt)),
      el("span", "order-qty", grams(order.quantityInGrams)),
      el("span", `status status-${order.status}`, order.status),
    );
    container.append(row);
  }

  observeReveals(container);
}

function fillSelect(select, items, placeholder, label, disabled) {
  const previous = select.value;
  select.replaceChildren(new Option(placeholder, ""));

  for (const item of items) {
    const option = new Option(label(item), item.id);
    option.disabled = disabled ? disabled(item) : false;
    select.append(option);
  }

  if (previous && select.querySelector(`option[value="${CSS.escape(previous)}"]`)) {
    select.value = previous;
  }
}

function renderSelects() {
  fillSelect(
    $("#grainId"),
    state.grains,
    t("select.grain"),
    (grain) => t("select.grainOption", { name: grain.name, amount: grams(grain.totalOnInventory) }),
    (grain) => grain.totalOnInventory === 0,
  );

  fillSelect(
    $("#preparationMethodId"),
    state.methods,
    t("select.method"),
    (method) => `${method.name} · ${minutes(method.timeInMinutes)}`,
  );
}

function updateStockHint() {
  const hint = $("[data-stock-hint]");
  const grain = state.grains.find((item) => String(item.id) === $("#grainId").value);
  const requested = Number($("#quantityInGrams").value) || 0;

  if (!grain) {
    hint.textContent = "";
    hint.classList.remove("is-warn");
    return;
  }

  const enough = grain.totalOnInventory >= requested;
  const vars = { amount: grams(grain.totalOnInventory), name: grain.name };
  hint.textContent = enough ? t("hint.remaining", vars) : t("hint.only", vars);
  hint.classList.toggle("is-warn", !enough);
}

function renderStats() {
  const totalStock = state.grains.reduce((sum, grain) => sum + grain.totalOnInventory, 0);
  $('[data-stat="grains"]').textContent = state.grains.length;
  $('[data-stat="methods"]').textContent = state.methods.length;
  $('[data-stat="stock"]').textContent = grams(totalStock);
}

function renderAll() {
  applyTranslations();
  renderGrains();
  renderMethods();
  renderOrders();
  renderSelects();
  renderStats();
  updateStockHint();
}

/* ---------- data ---------- */

async function loadCatalog() {
  try {
    const [grains, methods] = await Promise.all([api("/grains"), api("/preparation-methods")]);
    state.grains = grains.data ?? [];
    state.methods = methods.data ?? [];
  } catch (error) {
    toast(t("toast.catalogFail"), error.message, "is-error");
  }
}

async function loadOrders() {
  try {
    const orders = await api("/orders");
    state.orders = orders.data ?? [];
  } catch (error) {
    toast(t("toast.ordersFail"), error.message, "is-error");
  }
}

async function refresh() {
  await Promise.all([loadCatalog(), loadOrders()]);
  renderAll();
}

/* ---------- interactions ---------- */

function bindOrderForm() {
  const form = $("[data-order-form]");
  const quantity = $("#quantityInGrams");
  const submit = $("[data-submit]");

  $$(".chips button").forEach((chip) => {
    chip.addEventListener("click", () => {
      $$(".chips button").forEach((other) => other.classList.remove("is-active"));
      chip.classList.add("is-active");
      quantity.value = chip.dataset.qty;
      updateStockHint();
    });
  });

  quantity.addEventListener("input", () => {
    $$(".chips button").forEach((chip) =>
      chip.classList.toggle("is-active", chip.dataset.qty === quantity.value),
    );
    updateStockHint();
  });

  $("#grainId").addEventListener("change", updateStockHint);

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const payload = {
      grainId: Number($("#grainId").value),
      preparationMethodId: Number($("#preparationMethodId").value),
      quantityInGrams: Number(quantity.value),
    };

    if (!payload.grainId || !payload.preparationMethodId) {
      toast(t("toast.missingTitle"), t("toast.missingBody"), "is-error");
      return;
    }

    submit.disabled = true;
    try {
      const result = await api("/orders", { method: "POST", body: JSON.stringify(payload) });
      toast(result.message, t("toast.orderBody", { amount: grams(payload.quantityInGrams) }), "is-success");
      await refresh();
      $("#pedidos").scrollIntoView({ block: "start" });
    } catch (error) {
      toast(error.message, detailsText(error.details), "is-error");
    } finally {
      submit.disabled = false;
    }
  });
}

function bindLanguage() {
  $$(".lang-switch button").forEach((button) => {
    button.classList.toggle("is-active", button.dataset.lang === state.lang);
    button.addEventListener("click", async () => {
      state.lang = button.dataset.lang;
      localStorage.setItem("cr-lang", state.lang);
      $$(".lang-switch button").forEach((other) =>
        other.classList.toggle("is-active", other === button),
      );
      renderAll();
      toast(t("toast.langTitle"), t("toast.langBody"));
    });
  });
}

/* ---------- chrome ---------- */

const revealObserver = new IntersectionObserver(
  (entries) => {
    for (const entry of entries) {
      if (!entry.isIntersecting) continue;
      entry.target.classList.add("is-visible");
      revealObserver.unobserve(entry.target);
    }
  },
  { threshold: 0.12, rootMargin: "0px 0px -60px 0px" },
);

function observeReveals(scope = document) {
  $$(".reveal:not(.is-visible)", scope).forEach((node) => revealObserver.observe(node));
}

function bindHeader() {
  const header = $("#header");
  const onScroll = () => header.classList.toggle("is-stuck", window.scrollY > 12);
  document.addEventListener("scroll", onScroll, { passive: true });
  onScroll();
}

/* ---------- boot ---------- */

applyTranslations();
bindHeader();
bindLanguage();
bindOrderForm();
observeReveals();
$("[data-refresh-orders]").addEventListener("click", refresh);
refresh();
