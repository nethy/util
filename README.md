# io.nethy.util

Basic utility classes for helping everyday coding in java.

## ObjectPresenter

It helps implementing the `Object.toString` methods.

For the `of` method, pass the current instance and use the `append` methods to
add fields for representation. At the and invoke the `toString` method to
return the actual value.

```java
ObjectPresenter.of(this)
               .append("name", name)
               .append("address", address)
               .toString();
```

## ObjectComparator

It makes easier to implement the `Object.equals` methods.

Use the `isEqual` method to define comparison between two instances.

1. If `this == obj`, then method will return `true`.
1. If `obj == null`, then method will return `false`.
1. If `obj` is a subtype of `this`, then it will be casted and pass to the
   predicate.
1. If `this` is a subtype of `obj`, then method will return `obj.equals(this)`.
1. Otherwise the method will return `false`.

```java
public class AClass {
  private int a;
  private String b;

  @Override
  public boolean equals(Object obj) {
    return ObjectComparator.isEqual(this, obj, (current, other) -> 
        curent.a == other.a
        && current.b == other.b;
    );
  }
}
```

## Validation

Helps in validation multiple arguments and aggregates errors into a list.

Methods:
* `isValid` - determines it is valid or not.
* `and` - aggregates multiple `Validation`s.
* `map` - transforms valid value.
* `reduce` - transforms `Validation` into other type.
