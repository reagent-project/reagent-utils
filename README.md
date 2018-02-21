## reagent-utils


This library provides a number of convenience helpers for use with Reagent.

[![Clojars Project](http://clojars.org/reagent-utils/latest-version.svg)](https://clojars.org/reagent-utils)

<div class="namespace-docs" id="content" style="left: 392px;"><h1 class="anchor" id="top">reagent.cookies</h1><div class="doc"><pre class="plaintext"></pre></div><div class="public anchor" id="var-clear.21"><h3>clear!</h3><div class="usage"><code>(clear!)</code></div><div class="doc"><pre class="plaintext">removes all cookies
</pre></div></div><div class="public anchor" id="var-contains-key.3F"><h3>contains-key?</h3><div class="usage"><code>(contains-key? k)</code></div><div class="doc"><pre class="plaintext">is the key present in the cookies
</pre></div></div><div class="public anchor" id="var-contains-val.3F"><h3>contains-val?</h3><div class="usage"><code>(contains-val? v)</code></div><div class="doc"><pre class="plaintext">is the value present in the cookies (as string)
</pre></div></div><div class="public anchor" id="var-count"><h3>count</h3><div class="usage"><code>(count)</code></div><div class="doc"><pre class="plaintext">returns the number of cookies
</pre></div></div><div class="public anchor" id="var-empty.3F"><h3>empty?</h3><div class="usage"><code>(empty?)</code></div><div class="doc"><pre class="plaintext">true if no cookies are set
</pre></div></div><div class="public anchor" id="var-get"><h3>get</h3><div class="usage"><code>(get k &amp; [default])</code></div><div class="doc"><pre class="plaintext">gets the value at the key (as edn), optional default when value is not found
</pre></div></div><div class="public anchor" id="var-get-raw"><h3>get-raw</h3><div class="usage"><code>(get-raw k &amp; [default])</code></div><div class="doc"><pre class="plaintext">gets the value at the key (as string), optional default when value is not found
</pre></div></div><div class="public anchor" id="var-keys"><h3>keys</h3><div class="usage"><code>(keys)</code></div><div class="doc"><pre class="plaintext">returns all the keys for the cookies
</pre></div></div><div class="public anchor" id="var-raw-vals"><h3>raw-vals</h3><div class="usage"><code>(raw-vals)</code></div><div class="doc"><pre class="plaintext">returns cookie values (as strings)
</pre></div></div><div class="public anchor" id="var-remove.21"><h3>remove!</h3><div class="usage"><code>(remove! k)</code><code>(remove! k path domain)</code></div><div class="doc"><pre class="plaintext">removes a cookie, optionally for a specific path and/or domain
</pre></div></div><div class="public anchor" id="var-set.21"><h3>set!</h3><div class="usage"><code>(set! k content &amp; [{:keys [max-age path domain secure? raw?]} :as opts])</code></div><div class="doc"><pre class="plaintext">sets a cookie, the max-age for session cookie
following optional parameters may be passed in as a map:
:max-age - defaults to -1
:path - path of the cookie, defaults to the full request path
:domain - domain of the cookie, when null the browser will use the full request host name
:secure? - boolean specifying whether the cookie should only be sent over a secure channel
:raw? - boolean specifying whether content should be stored raw, or as EDN
</pre></div></div><div class="public anchor" id="var-vals"><h3>vals</h3><div class="usage"><code>(vals)</code></div><div class="doc"><pre class="plaintext">returns cookie values (as edn)
</pre></div></div></div>

<div class="namespace-docs" id="content" style="left: 392px;"><h1 class="anchor" id="top">reagent.crypt</h1><div class="doc"><pre class="plaintext"></pre></div><div class="public anchor" id="var-bytes-.3Ehex"><h3>bytes-&gt;hex</h3><div class="usage"><code>(bytes-&gt;hex bytes-in)</code></div><div class="doc"><pre class="plaintext">convert bytes to hex
</pre></div></div><div class="public anchor" id="var-digest"><h3>digest</h3><div class="usage"><code>(digest hasher bytes)</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-hash"><h3>hash</h3><div class="usage"><code>(hash s hash-type &amp; [hex?])</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-hash-bytes"><h3>hash-bytes</h3><div class="usage"><code>(hash-bytes s hash-type)</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-string-.3Ebytes"><h3>string-&gt;bytes</h3><div class="usage"><code>(string-&gt;bytes s)</code></div><div class="doc"><pre class="plaintext"></pre></div></div></div>

<div class="namespace-docs" id="content" style="left: 392px;"><h1 class="anchor" id="top">reagent.format</h1><div class="doc"><pre class="plaintext"></pre></div><div class="public anchor" id="var-add-slashes"><h3>add-slashes</h3><div class="usage"><code>(add-slashes s)</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-capitalize-words"><h3>capitalize-words</h3><div class="usage"><code>(capitalize-words s)</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-center"><h3>center</h3><div class="usage"><code>(center text w)</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-currency-format"><h3>currency-format</h3><div class="usage"><code>(currency-format n)</code></div><div class="doc"><pre class="plaintext">formats currency using the current locale
to change locale set goog.i18n.NumberFormatSymbols eg:
(set! goog.i18n.NumberFormatSymbols goog.i18n.NumberFormatSymbols_it_IT)
see here for supported locales
<a href="https://github.com/google/closure-library/blob/master/closure/goog/i18n/compactnumberformatsymbols.js">https://github.com/google/closure-library/blob/master/closure/goog/i18n/compactnumberformatsymbols.js</a>
</pre></div></div><div class="public anchor" id="var-date-format"><h3>date-format</h3><div class="usage"><code>(date-format date fmt &amp; [tz])</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-encode-uri"><h3>encode-uri</h3><div class="usage"><code>(encode-uri uri)</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-format"><h3>format</h3><div class="usage"><code>(format fmt &amp; args)</code></div><div class="doc"><pre class="plaintext">Formats a string using goog.string.format.
e.g: (format "Cost: %.2f" 10.0234)</pre></div></div><div class="public anchor" id="var-line-numbers"><h3>line-numbers</h3><div class="usage"><code>(line-numbers s)</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-pluralize"><h3>pluralize</h3><div class="usage"><code>(pluralize items &amp; [word ending1 ending2 :as opts])</code></div><div class="doc"><pre class="plaintext">pluralizes the word based on the number of items
(util/pluralize ["John"] "lad")
(util/pluralize ["John" "James"] "lad")
(util/pluralize ["Alice"] "lad" "y" "ies")</pre></div></div><div class="public anchor" id="var-printf"><h3>printf</h3><div class="usage"><code>(printf fmt &amp; args)</code></div><div class="doc"><pre class="plaintext">Prints formatted output, as per format
</pre></div></div><div class="public anchor" id="var-remove-tags"><h3>remove-tags</h3><div class="usage"><code>(remove-tags s &amp; tags)</code></div><div class="doc"><pre class="plaintext">removes specified tags, eg:
(remove-tags "&lt;p&gt;foo bar&lt;/p&gt;" "p")</pre></div></div></div>

<div class="namespace-docs" id="content" style="left: 392px;"><h1 class="anchor" id="top">reagent.session</h1><div class="doc"><pre class="plaintext"></pre></div><div class="public anchor" id="var-assoc-in.21"><h3>assoc-in!</h3><div class="usage"><code>(assoc-in! ks v)</code></div><div class="doc"><pre class="plaintext">Associates a value in the session, where ks is a
sequence of keys and v is the new value and returns
a new nested structure. If any levels do not exist,
hash-maps will be created.</pre></div></div><div class="public anchor" id="var-clear.21"><h3>clear!</h3><div class="usage"><code>(clear!)</code></div><div class="doc"><pre class="plaintext">Remove all data from the session and start over cleanly.
</pre></div></div><div class="public anchor" id="var-get"><h3>get</h3><div class="usage"><code>(get k &amp; [default])</code></div><div class="doc"><pre class="plaintext">Get the key's value from the session, returns nil if it doesn't exist.
</pre></div></div><div class="public anchor" id="var-get.21"><h3>get!</h3><div class="usage"><code>(get! k &amp; [default])</code></div><div class="doc"><pre class="plaintext">Destructive get from the session. This returns the current value of the key
and then removes it from the session.</pre></div></div><div class="public anchor" id="var-get-in"><h3>get-in</h3><div class="usage"><code>(get-in ks &amp; [default])</code></div><div class="doc"><pre class="plaintext">Gets the value at the path specified by the vector ks from the session,
returns nil if it doesn't exist.</pre></div></div><div class="public anchor" id="var-get-in.21"><h3>get-in!</h3><div class="usage"><code>(get-in! ks &amp; [default])</code></div><div class="doc"><pre class="plaintext">Destructive get from the session. This returns the current value of the path
specified by the vector ks and then removes it from the session.</pre></div></div><div class="public anchor" id="var-put.21"><h3>put!</h3><div class="usage"><code>(put! k v)</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-remove.21"><h3>remove!</h3><div class="usage"><code>(remove! k)</code></div><div class="doc"><pre class="plaintext">Remove a key from the session
</pre></div></div><div class="public anchor" id="var-reset.21"><h3>reset!</h3><div class="usage"><code>(reset! m)</code></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-state"><h3>state</h3><div class="usage"></div><div class="doc"><pre class="plaintext"></pre></div></div><div class="public anchor" id="var-swap.21"><h3>swap!</h3><div class="usage"><code>(swap! f &amp; args)</code></div><div class="doc"><pre class="plaintext">Replace the current session's value with the result of executing f with
the current value and args.</pre></div></div><div class="public anchor" id="var-update.21"><h3>update!</h3><div class="usage"><code>(update! k f &amp; args)</code></div><div class="doc"><pre class="plaintext">Updates a value in session where k is a key and f
is the function that takes the old value along with any
supplied args and return the new value. If key is not
present it will be added.</pre></div></div><div class="public anchor" id="var-update-in.21"><h3>update-in!</h3><div class="usage"><code>(update-in! ks f &amp; args)</code></div><div class="doc"><pre class="plaintext">'Updates a value in the session, where ks is a
sequence of keys and f is a function that will
take the old value along with any supplied args and return
the new value. If any levels do not exist, hash-maps
will be created.</pre></div></div></div>

<div class="namespace-docs" id="content" style="left: 392px;"><h1 class="anchor" id="top">reagent.validation</h1><div class="doc"><pre class="plaintext">Functions for validating input and setting string errors on fields.
</pre></div><div class="public anchor" id="var-equal-to.3F"><h3>equal-to?</h3><div class="usage"><code>(equal-to? v n)</code></div><div class="doc"><pre class="plaintext">Returns true if the string represents a number = given.
</pre></div></div><div class="public anchor" id="var-greater-than.3F"><h3>greater-than?</h3><div class="usage"><code>(greater-than? v n)</code></div><div class="doc"><pre class="plaintext">Returns true if the string represents a number &gt; given.
</pre></div></div><div class="public anchor" id="var-has-value.3F"><h3>has-value?</h3><div class="usage"><code>(has-value? v)</code></div><div class="doc"><pre class="plaintext">Returns true if v is truthy and not an empty string.
</pre></div></div><div class="public anchor" id="var-has-values.3F"><h3>has-values?</h3><div class="usage"><code>(has-values? coll)</code></div><div class="doc"><pre class="plaintext">Returns true if all members of the collection has-value? This works on maps as well.
</pre></div></div><div class="public anchor" id="var-is-email.3F"><h3>is-email?</h3><div class="usage"><code>(is-email? v)</code></div><div class="doc"><pre class="plaintext">Returns true if v is an email address
</pre></div></div><div class="public anchor" id="var-less-than.3F"><h3>less-than?</h3><div class="usage"><code>(less-than? v n)</code></div><div class="doc"><pre class="plaintext">Returns true if the string represents a number &lt; given.
</pre></div></div><div class="public anchor" id="var-matches-regex.3F"><h3>matches-regex?</h3><div class="usage"><code>(matches-regex? v regex)</code></div><div class="doc"><pre class="plaintext">Returns true if the string matches the given regular expression
</pre></div></div><div class="public anchor" id="var-max-length.3F"><h3>max-length?</h3><div class="usage"><code>(max-length? v len)</code></div><div class="doc"><pre class="plaintext">Returns true if v is less than or equal to the given len
</pre></div></div><div class="public anchor" id="var-min-length.3F"><h3>min-length?</h3><div class="usage"><code>(min-length? v len)</code></div><div class="doc"><pre class="plaintext">Returns true if v is greater than or equal to the given len
</pre></div></div><div class="public anchor" id="var-not-nil.3F"><h3>not-nil?</h3><div class="usage"><code>(not-nil? v)</code></div><div class="doc"><pre class="plaintext">Returns true if v is not nil
</pre></div></div><div class="public anchor" id="var-valid-number.3F"><h3>valid-number?</h3><div class="usage"><code>(valid-number? v)</code></div><div class="doc"><pre class="plaintext">Returns true if the string can be parsed to a Long
</pre></div></div></div>


