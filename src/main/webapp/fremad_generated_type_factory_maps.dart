library fremad.web.fremad.generated_type_factory_maps;

import 'package:di/di.dart';
import 'package:di/src/reflector_static.dart';

import 'fremad.dart' as import_0;
import 'package:angular/core_dom/module_internal.dart' as import_1;
import 'package:angular/core/module_internal.dart' as import_2;
import 'package:perf_api/perf_api.dart' as import_3;
import 'package:di/src/injector.dart' as import_5;
import 'package:angular/core/formatter.dart' as import_6;
import 'package:angular/core/registry.dart' as import_7;
import 'package:angular/core/parser/parser.dart' as import_8;
import 'package:angular/change_detection/ast_parser.dart' as import_9;
import 'dart:html' as import_10;
import 'package:angular/change_detection/watch_group.dart' as import_11;
import 'package:angular/cache/module.dart' as import_12;
import 'package:angular/core/parser/dynamic_parser.dart' as import_13;
import 'package:angular/core/parser/lexer.dart' as import_14;
import 'package:angular/directive/module.dart' as import_15;
import 'package:angular/core_dom/directive_injector.dart' as import_16;
import 'package:angular/change_detection/change_detection.dart' as import_17;
import 'package:angular/formatter/module_internal.dart' as import_18;
import 'package:angular/animate/module.dart' as import_19;
import 'package:angular/routing/module.dart' as import_20;
import 'package:route_hierarchical/client.dart' as import_21;
import 'package:angular/application.dart' as import_22;
import 'package:angular/cache/js_cache_register.dart' as import_23;

final Key _KEY_ExceptionHandler = new Key(import_2.ExceptionHandler);
final Key _KEY_BrowserCookies = new Key(import_1.BrowserCookies);
final Key _KEY_Profiler = new Key(import_3.Profiler);
final Key _KEY_Expando = new Key(Expando);
final Key _KEY_Injector = new Key(import_5.Injector);
final Key _KEY_FormatterMap = new Key(import_6.FormatterMap);
final Key _KEY_MetadataExtractor = new Key(import_7.MetadataExtractor);
final Key _KEY_DirectiveSelectorFactory = new Key(import_1.DirectiveSelectorFactory);
final Key _KEY_Parser = new Key(import_8.Parser);
final Key _KEY_CompilerConfig = new Key(import_1.CompilerConfig);
final Key _KEY_ASTParser = new Key(import_9.ASTParser);
final Key _KEY_ComponentFactory = new Key(import_1.ComponentFactory);
final Key _KEY_ShadowDomComponentFactory = new Key(import_1.ShadowDomComponentFactory);
final Key _KEY_TranscludingComponentFactory = new Key(import_1.TranscludingComponentFactory);
final Key _KEY_Node = new Key(import_10.Node);
final Key _KEY_ShadowRoot = new Key(import_10.ShadowRoot);
final Key _KEY_HttpDefaultHeaders = new Key(import_1.HttpDefaultHeaders);
final Key _KEY_LocationWrapper = new Key(import_1.LocationWrapper);
final Key _KEY_UrlRewriter = new Key(import_1.UrlRewriter);
final Key _KEY_HttpBackend = new Key(import_1.HttpBackend);
final Key _KEY_HttpDefaults = new Key(import_1.HttpDefaults);
final Key _KEY_HttpInterceptors = new Key(import_1.HttpInterceptors);
final Key _KEY_RootScope = new Key(import_2.RootScope);
final Key _KEY_HttpConfig = new Key(import_1.HttpConfig);
final Key _KEY_VmTurnZone = new Key(import_2.VmTurnZone);
final Key _KEY_AST = new Key(import_11.AST);
final Key _KEY_Scope = new Key(import_2.Scope);
final Key _KEY_NodeAttrs = new Key(import_1.NodeAttrs);
final Key _KEY_String = new Key(String);
final Key _KEY_Element = new Key(import_10.Element);
final Key _KEY_Animate = new Key(import_1.Animate);
final Key _KEY_ElementBinderFactory = new Key(import_1.ElementBinderFactory);
final Key _KEY_Interpolate = new Key(import_2.Interpolate);
final Key _KEY_ViewCache = new Key(import_1.ViewCache);
final Key _KEY_Http = new Key(import_1.Http);
final Key _KEY_TemplateCache = new Key(import_1.TemplateCache);
final Key _KEY_WebPlatform = new Key(import_1.WebPlatform);
final Key _KEY_ComponentCssRewriter = new Key(import_1.ComponentCssRewriter);
final Key _KEY_NodeTreeSanitizer = new Key(import_10.NodeTreeSanitizer);
final Key _KEY_CacheRegister = new Key(import_12.CacheRegister);
final Key _KEY_ContentPort = new Key(import_1.ContentPort);
final Key _KEY_Compiler = new Key(import_1.Compiler);
final Key _KEY_Lexer = new Key(import_14.Lexer);
final Key _KEY_ParserBackend = new Key(import_8.ParserBackend);
final Key _KEY_ClosureMap = new Key(import_13.ClosureMap);
final Key _KEY_ElementProbe = new Key(import_1.ElementProbe);
final Key _KEY_NodeValidator = new Key(import_10.NodeValidator);
final Key _KEY_NgElement = new Key(import_1.NgElement);
final Key _KEY_ViewFactory = new Key(import_1.ViewFactory);
final Key _KEY_ViewPort = new Key(import_1.ViewPort);
final Key _KEY_DirectiveInjector = new Key(import_16.DirectiveInjector);
final Key _KEY_DirectiveMap = new Key(import_1.DirectiveMap);
final Key _KEY_NgModel = new Key(import_15.NgModel);
final Key _KEY_NgTrueValue = new Key(import_15.NgTrueValue);
final Key _KEY_NgFalseValue = new Key(import_15.NgFalseValue);
final Key _KEY_NgModelOptions = new Key(import_15.NgModelOptions);
final Key _KEY_NgBindTypeForDateLike = new Key(import_15.NgBindTypeForDateLike);
final Key _KEY_NgValue = new Key(import_15.NgValue);
final Key _KEY_BoundViewFactory = new Key(import_1.BoundViewFactory);
final Key _KEY_NgSwitch = new Key(import_15.NgSwitch);
final Key _KEY_InputSelect = new Key(import_15.InputSelect);
final Key _KEY_ScopeStatsEmitter = new Key(import_2.ScopeStatsEmitter);
final Key _KEY_ScopeStatsConfig = new Key(import_2.ScopeStatsConfig);
final Key _KEY_Object = new Key(Object);
final Key _KEY_FieldGetterFactory = new Key(import_17.FieldGetterFactory);
final Key _KEY_ScopeDigestTTL = new Key(import_2.ScopeDigestTTL);
final Key _KEY_ScopeStats = new Key(import_2.ScopeStats);
final Key _KEY_AnimationFrame = new Key(import_19.AnimationFrame);
final Key _KEY_Window = new Key(import_10.Window);
final Key _KEY_AnimationLoop = new Key(import_19.AnimationLoop);
final Key _KEY_CssAnimationMap = new Key(import_19.CssAnimationMap);
final Key _KEY_AnimationOptimizer = new Key(import_19.AnimationOptimizer);
final Key _KEY_RouteInitializer = new Key(import_20.RouteInitializer);
final Key _KEY_Router = new Key(import_21.Router);
final Key _KEY_Application = new Key(import_22.Application);
final Key _KEY_NgRoutingHelper = new Key(import_20.NgRoutingHelper);
final Map<Type, Function> typeFactories = <Type, Function>{
  import_0.Routes: () => new import_0.Routes(),
  import_1.Animate: () => new import_1.Animate(),
  import_1.BrowserCookies: (a1) => new import_1.BrowserCookies(a1),
  import_1.Cookies: (a1) => new import_1.Cookies(a1),
  import_1.Compiler: (a1, a2) => new import_1.Compiler(a1, a2),
  import_1.CompilerConfig: () => new import_1.CompilerConfig(),
  import_1.DirectiveMap: (a1, a2, a3, a4) => new import_1.DirectiveMap(a1, a2, a3, a4),
  import_1.ElementBinderFactory: (a1, a2, a3, a4, a5, a6, a7, a8) => new import_1.ElementBinderFactory(a1, a2, a3, a4, a5, a6, a7, a8),
  import_1.EventHandler: (a1, a2, a3) => new import_1.EventHandler(a1, a2, a3),
  import_1.ShadowRootEventHandler: (a1, a2, a3) => new import_1.ShadowRootEventHandler(a1, a2, a3),
  import_1.UrlRewriter: () => new import_1.UrlRewriter(),
  import_1.HttpBackend: () => new import_1.HttpBackend(),
  import_1.LocationWrapper: () => new import_1.LocationWrapper(),
  import_1.HttpInterceptors: () => new import_1.HttpInterceptors(),
  import_1.HttpDefaultHeaders: () => new import_1.HttpDefaultHeaders(),
  import_1.HttpDefaults: (a1) => new import_1.HttpDefaults(a1),
  import_1.Http: (a1, a2, a3, a4, a5, a6, a7, a8, a9) => new import_1.Http(a1, a2, a3, a4, a5, a6, a7, a8, a9),
  import_1.HttpConfig: () => new import_1.HttpConfig(),
  import_1.TextMustache: (a1, a2, a3) => new import_1.TextMustache(a1, a2, a3),
  import_1.AttrMustache: (a1, a2, a3, a4) => new import_1.AttrMustache(a1, a2, a3, a4),
  import_1.NgElement: (a1, a2, a3) => new import_1.NgElement(a1, a2, a3),
  import_1.DirectiveSelectorFactory: (a1, a2, a3, a4, a5) => new import_1.DirectiveSelectorFactory(a1, a2, a3, a4, a5),
  import_1.ShadowDomComponentFactory: (a1, a2, a3, a4, a5, a6, a7, a8, a9) => new import_1.ShadowDomComponentFactory(a1, a2, a3, a4, a5, a6, a7, a8, a9),
  import_1.ComponentCssRewriter: () => new import_1.ComponentCssRewriter(),
  import_1.Content: (a1, a2) => new import_1.Content(a1, a2),
  import_1.TranscludingComponentFactory: (a1, a2, a3) => new import_1.TranscludingComponentFactory(a1, a2, a3),
  import_1.NullTreeSanitizer: () => new import_1.NullTreeSanitizer(),
  import_1.ViewCache: (a1, a2, a3, a4, a5) => new import_1.ViewCache(a1, a2, a3, a4, a5),
  import_1.WebPlatform: () => new import_1.WebPlatform(),
  import_6.FormatterMap: (a1, a2) => new import_6.FormatterMap(a1, a2),
  import_13.DynamicParser: (a1, a2, a3) => new import_13.DynamicParser(a1, a2, a3),
  import_13.DynamicParserBackend: (a1) => new import_13.DynamicParserBackend(a1),
  import_14.Lexer: () => new import_14.Lexer(),
  import_12.CacheRegister: () => new import_12.CacheRegister(),
  import_15.AHref: (a1, a2) => new import_15.AHref(a1, a2),
  import_15.NgBaseCss: () => new import_15.NgBaseCss(),
  import_15.NgBind: (a1, a2) => new import_15.NgBind(a1, a2),
  import_15.NgBindHtml: (a1, a2) => new import_15.NgBindHtml(a1, a2),
  import_15.NgBindTemplate: (a1) => new import_15.NgBindTemplate(a1),
  import_15.NgClass: (a1, a2, a3) => new import_15.NgClass(a1, a2, a3),
  import_15.NgClassOdd: (a1, a2, a3) => new import_15.NgClassOdd(a1, a2, a3),
  import_15.NgClassEven: (a1, a2, a3) => new import_15.NgClassEven(a1, a2, a3),
  import_15.NgEvent: (a1, a2) => new import_15.NgEvent(a1, a2),
  import_15.NgCloak: (a1, a2) => new import_15.NgCloak(a1, a2),
  import_15.NgIf: (a1, a2, a3) => new import_15.NgIf(a1, a2, a3),
  import_15.NgUnless: (a1, a2, a3) => new import_15.NgUnless(a1, a2, a3),
  import_15.NgInclude: (a1, a2, a3, a4, a5) => new import_15.NgInclude(a1, a2, a3, a4, a5),
  import_15.NgModel: (a1, a2, a3, a4, a5, a6) => new import_15.NgModel(a1, a2, a3, a4, a5, a6),
  import_15.InputCheckbox: (a1, a2, a3, a4, a5, a6) => new import_15.InputCheckbox(a1, a2, a3, a4, a5, a6),
  import_15.InputTextLike: (a1, a2, a3, a4) => new import_15.InputTextLike(a1, a2, a3, a4),
  import_15.InputNumberLike: (a1, a2, a3, a4) => new import_15.InputNumberLike(a1, a2, a3, a4),
  import_15.NgBindTypeForDateLike: (a1) => new import_15.NgBindTypeForDateLike(a1),
  import_15.InputDateLike: (a1, a2, a3, a4, a5) => new import_15.InputDateLike(a1, a2, a3, a4, a5),
  import_15.NgValue: (a1) => new import_15.NgValue(a1),
  import_15.NgTrueValue: (a1) => new import_15.NgTrueValue(a1),
  import_15.NgFalseValue: (a1) => new import_15.NgFalseValue(a1),
  import_15.InputRadio: (a1, a2, a3, a4, a5) => new import_15.InputRadio(a1, a2, a3, a4, a5),
  import_15.ContentEditable: (a1, a2, a3, a4) => new import_15.ContentEditable(a1, a2, a3, a4),
  import_15.NgPluralize: (a1, a2, a3, a4) => new import_15.NgPluralize(a1, a2, a3, a4),
  import_15.NgRepeat: (a1, a2, a3, a4, a5) => new import_15.NgRepeat(a1, a2, a3, a4, a5),
  import_15.NgTemplate: (a1, a2) => new import_15.NgTemplate(a1, a2),
  import_15.NgHide: (a1, a2) => new import_15.NgHide(a1, a2),
  import_15.NgShow: (a1, a2) => new import_15.NgShow(a1, a2),
  import_15.NgBooleanAttribute: (a1) => new import_15.NgBooleanAttribute(a1),
  import_15.NgSource: (a1) => new import_15.NgSource(a1),
  import_15.NgAttribute: (a1) => new import_15.NgAttribute(a1),
  import_15.NgStyle: (a1, a2) => new import_15.NgStyle(a1, a2),
  import_15.NgSwitch: (a1) => new import_15.NgSwitch(a1),
  import_15.NgSwitchWhen: (a1, a2, a3, a4) => new import_15.NgSwitchWhen(a1, a2, a3, a4),
  import_15.NgSwitchDefault: (a1, a2, a3, a4) => new import_15.NgSwitchDefault(a1, a2, a3, a4),
  import_15.NgNonBindable: () => new import_15.NgNonBindable(),
  import_15.InputSelect: (a1, a2, a3, a4) => new import_15.InputSelect(a1, a2, a3, a4),
  import_15.OptionValue: (a1, a2, a3) => new import_15.OptionValue(a1, a2, a3),
  import_15.NgForm: (a1, a2, a3, a4) => new import_15.NgForm(a1, a2, a3, a4),
  import_15.NgModelRequiredValidator: (a1) => new import_15.NgModelRequiredValidator(a1),
  import_15.NgModelUrlValidator: (a1) => new import_15.NgModelUrlValidator(a1),
  import_15.NgModelColorValidator: (a1) => new import_15.NgModelColorValidator(a1),
  import_15.NgModelEmailValidator: (a1) => new import_15.NgModelEmailValidator(a1),
  import_15.NgModelNumberValidator: (a1) => new import_15.NgModelNumberValidator(a1),
  import_15.NgModelMaxNumberValidator: (a1) => new import_15.NgModelMaxNumberValidator(a1),
  import_15.NgModelMinNumberValidator: (a1) => new import_15.NgModelMinNumberValidator(a1),
  import_15.NgModelPatternValidator: (a1) => new import_15.NgModelPatternValidator(a1),
  import_15.NgModelMinLengthValidator: (a1) => new import_15.NgModelMinLengthValidator(a1),
  import_15.NgModelMaxLengthValidator: (a1) => new import_15.NgModelMaxLengthValidator(a1),
  import_15.NgModelOptions: () => new import_15.NgModelOptions(),
  import_2.ExceptionHandler: () => new import_2.ExceptionHandler(),
  import_2.Interpolate: (a1) => new import_2.Interpolate(a1),
  import_2.ScopeDigestTTL: () => new import_2.ScopeDigestTTL(),
  import_2.ScopeStats: (a1, a2) => new import_2.ScopeStats(a1, a2),
  import_2.ScopeStatsEmitter: () => new import_2.ScopeStatsEmitter(),
  import_2.ScopeStatsConfig: () => new import_2.ScopeStatsConfig(),
  import_2.RootScope: (a1, a2, a3, a4, a5, a6, a7, a8, a9, a10) => new import_2.RootScope(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10),
  import_9.ASTParser: (a1, a2) => new import_9.ASTParser(a1, a2),
  import_18.Currency: () => new import_18.Currency(),
  import_18.Date: () => new import_18.Date(),
  import_18.Filter: (a1) => new import_18.Filter(a1),
  import_18.Json: () => new import_18.Json(),
  import_18.LimitTo: (a1) => new import_18.LimitTo(a1),
  import_18.Lowercase: () => new import_18.Lowercase(),
  import_18.Arrayify: () => new import_18.Arrayify(),
  import_18.Number: () => new import_18.Number(),
  import_18.OrderBy: (a1) => new import_18.OrderBy(a1),
  import_18.Uppercase: () => new import_18.Uppercase(),
  import_18.Stringify: () => new import_18.Stringify(),
  import_19.AnimationLoop: (a1, a2, a3) => new import_19.AnimationLoop(a1, a2, a3),
  import_19.AnimationFrame: (a1) => new import_19.AnimationFrame(a1),
  import_19.AnimationOptimizer: (a1) => new import_19.AnimationOptimizer(a1),
  import_19.CssAnimate: (a1, a2, a3) => new import_19.CssAnimate(a1, a2, a3),
  import_19.CssAnimationMap: () => new import_19.CssAnimationMap(),
  import_19.NgAnimate: (a1, a2) => new import_19.NgAnimate(a1, a2),
  import_19.NgAnimateChildren: (a1, a2) => new import_19.NgAnimateChildren(a1, a2),
  import_20.NgRoutingUsePushState: () => new import_20.NgRoutingUsePushState(),
  import_20.NgRoutingHelper: (a1, a2, a3, a4) => new import_20.NgRoutingHelper(a1, a2, a3, a4),
  import_20.NgView: (a1, a2, a3, a4, a5, a6) => new import_20.NgView(a1, a2, a3, a4, a5, a6),
  import_20.NgBindRoute: (a1, a2, a3) => new import_20.NgBindRoute(a1, a2, a3),
  import_23.JsCacheRegister: (a1) => new import_23.JsCacheRegister(a1),
  import_3.Profiler: () => new import_3.Profiler(),
};
final Map<Type, List<Key>> parameterKeys = {
  import_0.Routes: const[],
  import_1.Animate: const[],
  import_1.BrowserCookies: [_KEY_ExceptionHandler],
  import_1.Cookies: [_KEY_BrowserCookies],
  import_1.Compiler: [_KEY_Profiler, _KEY_Expando],
  import_1.CompilerConfig: const[],
  import_1.DirectiveMap: [_KEY_Injector, _KEY_FormatterMap, _KEY_MetadataExtractor, _KEY_DirectiveSelectorFactory],
  import_1.ElementBinderFactory: [_KEY_Parser, _KEY_Profiler, _KEY_CompilerConfig, _KEY_Expando, _KEY_ASTParser, _KEY_ComponentFactory, _KEY_ShadowDomComponentFactory, _KEY_TranscludingComponentFactory],
  import_1.EventHandler: [_KEY_Node, _KEY_Expando, _KEY_ExceptionHandler],
  import_1.ShadowRootEventHandler: [_KEY_ShadowRoot, _KEY_Expando, _KEY_ExceptionHandler],
  import_1.UrlRewriter: const[],
  import_1.HttpBackend: const[],
  import_1.LocationWrapper: const[],
  import_1.HttpInterceptors: const[],
  import_1.HttpDefaultHeaders: const[],
  import_1.HttpDefaults: [_KEY_HttpDefaultHeaders],
  import_1.Http: [_KEY_BrowserCookies, _KEY_LocationWrapper, _KEY_UrlRewriter, _KEY_HttpBackend, _KEY_HttpDefaults, _KEY_HttpInterceptors, _KEY_RootScope, _KEY_HttpConfig, _KEY_VmTurnZone],
  import_1.HttpConfig: const[],
  import_1.TextMustache: [_KEY_Node, _KEY_AST, _KEY_Scope],
  import_1.AttrMustache: [_KEY_NodeAttrs, _KEY_String, _KEY_AST, _KEY_Scope],
  import_1.NgElement: [_KEY_Element, _KEY_Scope, _KEY_Animate],
  import_1.DirectiveSelectorFactory: [_KEY_ElementBinderFactory, _KEY_Interpolate, _KEY_ASTParser, _KEY_FormatterMap, _KEY_Injector],
  import_1.ShadowDomComponentFactory: [_KEY_ViewCache, _KEY_Http, _KEY_TemplateCache, _KEY_WebPlatform, _KEY_ComponentCssRewriter, _KEY_NodeTreeSanitizer, _KEY_Expando, _KEY_CompilerConfig, _KEY_CacheRegister],
  import_1.ComponentCssRewriter: const[],
  import_1.Content: [_KEY_ContentPort, _KEY_Element],
  import_1.TranscludingComponentFactory: [_KEY_Expando, _KEY_ViewCache, _KEY_CompilerConfig],
  import_1.NullTreeSanitizer: const[],
  import_1.ViewCache: [_KEY_Http, _KEY_TemplateCache, _KEY_Compiler, _KEY_NodeTreeSanitizer, _KEY_CacheRegister],
  import_1.WebPlatform: const[],
  import_6.FormatterMap: [_KEY_Injector, _KEY_MetadataExtractor],
  import_13.DynamicParser: [_KEY_Lexer, _KEY_ParserBackend, _KEY_CacheRegister],
  import_13.DynamicParserBackend: [_KEY_ClosureMap],
  import_14.Lexer: const[],
  import_12.CacheRegister: const[],
  import_15.AHref: [_KEY_Element, _KEY_VmTurnZone],
  import_15.NgBaseCss: const[],
  import_15.NgBind: [_KEY_Element, _KEY_ElementProbe],
  import_15.NgBindHtml: [_KEY_Element, _KEY_NodeValidator],
  import_15.NgBindTemplate: [_KEY_Element],
  import_15.NgClass: [_KEY_NgElement, _KEY_Scope, _KEY_NodeAttrs],
  import_15.NgClassOdd: [_KEY_NgElement, _KEY_Scope, _KEY_NodeAttrs],
  import_15.NgClassEven: [_KEY_NgElement, _KEY_Scope, _KEY_NodeAttrs],
  import_15.NgEvent: [_KEY_Element, _KEY_Scope],
  import_15.NgCloak: [_KEY_Element, _KEY_Animate],
  import_15.NgIf: [_KEY_ViewFactory, _KEY_ViewPort, _KEY_Scope],
  import_15.NgUnless: [_KEY_ViewFactory, _KEY_ViewPort, _KEY_Scope],
  import_15.NgInclude: [_KEY_Element, _KEY_Scope, _KEY_ViewCache, _KEY_DirectiveInjector, _KEY_DirectiveMap],
  import_15.NgModel: [_KEY_Scope, _KEY_NgElement, _KEY_DirectiveInjector, _KEY_NodeAttrs, _KEY_Animate, _KEY_ElementProbe],
  import_15.InputCheckbox: [_KEY_Element, _KEY_NgModel, _KEY_Scope, _KEY_NgTrueValue, _KEY_NgFalseValue, _KEY_NgModelOptions],
  import_15.InputTextLike: [_KEY_Element, _KEY_NgModel, _KEY_Scope, _KEY_NgModelOptions],
  import_15.InputNumberLike: [_KEY_Element, _KEY_NgModel, _KEY_Scope, _KEY_NgModelOptions],
  import_15.NgBindTypeForDateLike: [_KEY_Element],
  import_15.InputDateLike: [_KEY_Element, _KEY_NgModel, _KEY_Scope, _KEY_NgBindTypeForDateLike, _KEY_NgModelOptions],
  import_15.NgValue: [_KEY_Element],
  import_15.NgTrueValue: [_KEY_Element],
  import_15.NgFalseValue: [_KEY_Element],
  import_15.InputRadio: [_KEY_Element, _KEY_NgModel, _KEY_Scope, _KEY_NgValue, _KEY_NodeAttrs],
  import_15.ContentEditable: [_KEY_Element, _KEY_NgModel, _KEY_Scope, _KEY_NgModelOptions],
  import_15.NgPluralize: [_KEY_Scope, _KEY_Element, _KEY_Interpolate, _KEY_FormatterMap],
  import_15.NgRepeat: [_KEY_ViewPort, _KEY_BoundViewFactory, _KEY_Scope, _KEY_Parser, _KEY_FormatterMap],
  import_15.NgTemplate: [_KEY_Element, _KEY_TemplateCache],
  import_15.NgHide: [_KEY_Element, _KEY_Animate],
  import_15.NgShow: [_KEY_Element, _KEY_Animate],
  import_15.NgBooleanAttribute: [_KEY_NgElement],
  import_15.NgSource: [_KEY_NgElement],
  import_15.NgAttribute: [_KEY_NodeAttrs],
  import_15.NgStyle: [_KEY_Element, _KEY_Scope],
  import_15.NgSwitch: [_KEY_Scope],
  import_15.NgSwitchWhen: [_KEY_NgSwitch, _KEY_ViewPort, _KEY_BoundViewFactory, _KEY_Scope],
  import_15.NgSwitchDefault: [_KEY_NgSwitch, _KEY_ViewPort, _KEY_BoundViewFactory, _KEY_Scope],
  import_15.NgNonBindable: const[],
  import_15.InputSelect: [_KEY_Element, _KEY_NodeAttrs, _KEY_NgModel, _KEY_Scope],
  import_15.OptionValue: [_KEY_Element, _KEY_InputSelect, _KEY_NgValue],
  import_15.NgForm: [_KEY_Scope, _KEY_NgElement, _KEY_DirectiveInjector, _KEY_Animate],
  import_15.NgModelRequiredValidator: [_KEY_NgModel],
  import_15.NgModelUrlValidator: [_KEY_NgModel],
  import_15.NgModelColorValidator: [_KEY_NgModel],
  import_15.NgModelEmailValidator: [_KEY_NgModel],
  import_15.NgModelNumberValidator: [_KEY_NgModel],
  import_15.NgModelMaxNumberValidator: [_KEY_NgModel],
  import_15.NgModelMinNumberValidator: [_KEY_NgModel],
  import_15.NgModelPatternValidator: [_KEY_NgModel],
  import_15.NgModelMinLengthValidator: [_KEY_NgModel],
  import_15.NgModelMaxLengthValidator: [_KEY_NgModel],
  import_15.NgModelOptions: const[],
  import_2.ExceptionHandler: const[],
  import_2.Interpolate: [_KEY_CacheRegister],
  import_2.ScopeDigestTTL: const[],
  import_2.ScopeStats: [_KEY_ScopeStatsEmitter, _KEY_ScopeStatsConfig],
  import_2.ScopeStatsEmitter: const[],
  import_2.ScopeStatsConfig: const[],
  import_2.RootScope: [_KEY_Object, _KEY_Parser, _KEY_ASTParser, _KEY_FieldGetterFactory, _KEY_FormatterMap, _KEY_ExceptionHandler, _KEY_ScopeDigestTTL, _KEY_VmTurnZone, _KEY_ScopeStats, _KEY_CacheRegister],
  import_9.ASTParser: [_KEY_Parser, _KEY_ClosureMap],
  import_18.Currency: const[],
  import_18.Date: const[],
  import_18.Filter: [_KEY_Parser],
  import_18.Json: const[],
  import_18.LimitTo: [_KEY_Injector],
  import_18.Lowercase: const[],
  import_18.Arrayify: const[],
  import_18.Number: const[],
  import_18.OrderBy: [_KEY_Parser],
  import_18.Uppercase: const[],
  import_18.Stringify: const[],
  import_19.AnimationLoop: [_KEY_AnimationFrame, _KEY_Profiler, _KEY_VmTurnZone],
  import_19.AnimationFrame: [_KEY_Window],
  import_19.AnimationOptimizer: [_KEY_Expando],
  import_19.CssAnimate: [_KEY_AnimationLoop, _KEY_CssAnimationMap, _KEY_AnimationOptimizer],
  import_19.CssAnimationMap: const[],
  import_19.NgAnimate: [_KEY_Element, _KEY_AnimationOptimizer],
  import_19.NgAnimateChildren: [_KEY_Element, _KEY_AnimationOptimizer],
  import_20.NgRoutingUsePushState: const[],
  import_20.NgRoutingHelper: [_KEY_RouteInitializer, _KEY_Injector, _KEY_Router, _KEY_Application],
  import_20.NgView: [_KEY_Element, _KEY_ViewCache, _KEY_DirectiveInjector, _KEY_Injector, _KEY_Router, _KEY_Scope],
  import_20.NgBindRoute: [_KEY_Router, _KEY_DirectiveInjector, _KEY_NgRoutingHelper],
  import_23.JsCacheRegister: [_KEY_CacheRegister],
  import_3.Profiler: const[],
};
setStaticReflectorAsDefault() => Module.DEFAULT_REFLECTOR = new GeneratedTypeFactories(typeFactories, parameterKeys);
