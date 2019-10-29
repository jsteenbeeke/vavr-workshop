package nl.topicus.devdev.vavr;

import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class VavrMatchers
{
	public static <T> TypeSafeDiagnosingMatcher<Option<T>> isNone()
	{
		return new TypeSafeDiagnosingMatcher<Option<T>>()
		{
			@Override protected boolean matchesSafely(Option<T> item, Description mismatchDescription)
			{
				if (!item.isEmpty())
				{
					mismatchDescription.appendText("is an option containing ")
							.appendValue(item.get());
					return false;
				}

								return true;
			}

			@Override public void describeTo(Description description)
			{
				description.appendText("is an empty option");
			}
		};
	}

	public static <T> TypeSafeDiagnosingMatcher<Option<T>> isSome(T value)
	{
		return new TypeSafeDiagnosingMatcher<Option<T>>()
		{
			@Override
			protected boolean matchesSafely(Option<T> item, Description mismatchDescription)
			{
				if (item.isEmpty())
				{
					mismatchDescription.appendText("is an empty option");


					return false;
				} else if (!Objects.equals(item.get(), value)) {
					mismatchDescription.appendText("is an option containing ")
							.appendValue(item.get());
					return false;
				}

				return true;
			}

			@Override
			public void describeTo(Description description)
			{
				description.appendText("is an option containing ")
						.appendValue(value);
			}
		};
	}

	public static <T> TypeSafeDiagnosingMatcher<Try<T>> isSuccess(@Nullable T value)
	{
		return new TypeSafeDiagnosingMatcher<Try<T>>()
		{
			@Override protected boolean matchesSafely(Try<T> item, Description mismatchDescription)
			{
				if (!item.isSuccess())
				{
					Throwable cause = item.getCause();

					mismatchDescription.appendText("is a failure of type ")
							.appendValue(cause.getClass().getName())
							.appendText(" with message ")
							.appendValue(cause.getMessage());
					return false;
				}

				if (!Objects.equals(item.get(), value)) {
					mismatchDescription.appendText("is a success with value ")
							.appendValue(item.get());

					return false;
				}

				return true;
			}

			@Override public void describeTo(Description description)
			{
				description.appendText("is a success with value ")
						.appendValue(value);
			}
		};
	}

	public static <T> TypeSafeDiagnosingMatcher<Try<T>> isFailure(
			@Nonnull Class<? extends Throwable> exceptionClass, @Nullable String message)
	{
		return new TypeSafeDiagnosingMatcher<Try<T>>()
		{
			@Override protected boolean matchesSafely(Try<T> item, Description mismatchDescription)
			{
				if (item.isSuccess())
				{
					mismatchDescription.appendText("is a success");
					return false;
				}

				Throwable cause = item.getCause();

				if (!exceptionClass.equals(cause.getClass()))
				{
					mismatchDescription.appendText("is a failure of type ")
							.appendValue(cause.getClass().getName());
					return false;
				}

				if (!Objects.equals(message, cause.getMessage()))
				{
					mismatchDescription.appendText("is a failure of type ")
							.appendValue(cause.getClass().getName())
							.appendText(" with message ")
							.appendValue(cause.getMessage());
					return false;
				}

				return true;
			}

			@Override public void describeTo(Description description)
			{
				description.appendText("is a failure of type ")
						.appendValue(exceptionClass.getName())
						.appendText(" with message ")
						.appendValue(message);
			}
		};
	}

	public static <T> TypeSafeDiagnosingMatcher<Either<?, T>> isRight(@Nullable T value)
	{
		return new TypeSafeDiagnosingMatcher<Either<?, T>>()
		{
			@Override public void describeTo(Description description)
			{
				description.appendText("is an Either.Right with value ").appendValue(value);
			}

			@Override
			protected boolean matchesSafely(Either<?, T> item, Description mismatchDescription)
			{
				if (item.isLeft())
				{
					mismatchDescription.appendText("is an Either.Left");
					return false;
				}

				T right = item.get();
				if (!Objects.equals(right, value))
				{
					mismatchDescription.appendText("is an Either.Right with value ")
							.appendValue(right);
					return false;
				}

				return true;
			}
		};
	}

	public static <T> TypeSafeDiagnosingMatcher<Either<T, ?>> isLeft(@Nonnull T value)
	{
		return new TypeSafeDiagnosingMatcher<Either<T, ?>>()
		{
			@Override public void describeTo(Description description)
			{
				description.appendText("is an Either.Left with value ").appendValue(value);
			}

			@Override
			protected boolean matchesSafely(Either<T, ?> item, Description mismatchDescription)
			{
				if (item.isRight())
				{
					mismatchDescription.appendText("is an Either.Right");
					return false;
				}

				if (!item.getLeft().equals(value))
				{
					mismatchDescription.appendText("is an Either.Left with value ")
							.appendValue(item.getLeft());
					return false;
				}

				return true;
			}
		};
	}

	public static <T> TypeSafeDiagnosingMatcher<Validation<?, T>> isValid(@Nonnull T value)
	{
		return new TypeSafeDiagnosingMatcher<Validation<?, T>>()
		{
			@Override public void describeTo(Description description)
			{
				description.appendText("is valid, with value ").appendValue(value);
			}

			@Override
			protected boolean matchesSafely(Validation<?, T> item, Description mismatchDescription)
			{
				if (item.isInvalid())
				{
					mismatchDescription.appendText("is invalid with error state ").appendValue(item.getError());
					return false;
				}

				if (!item.get().equals(value))
				{
					mismatchDescription.appendText("is valid with value ")
							.appendValue(item.get());
					return false;
				}

				return true;
			}
		};
	}

	public static <T> TypeSafeDiagnosingMatcher<Validation<T, ?>> isInvalid(@Nonnull T value)
	{
		return new TypeSafeDiagnosingMatcher<Validation<T, ?>>()
		{
			@Override
			public void describeTo(Description description)
			{
				description.appendText("is invalid, with error state ").appendValue(value);
			}

			@Override
			protected boolean matchesSafely(Validation<T, ?> item, Description mismatchDescription)
			{
				if (item.isValid())
				{
					mismatchDescription.appendText("is valid with value ")
							.appendValue(item.get());
					return false;
				}

				if (!item.getError().equals(value))
				{
					mismatchDescription.appendText("is invalid with error state ").appendValue(item.getError());
					return false;
				}

				return true;
			}
		};
	}
}
